package com.instagram.photoediting

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.instagram.imageeditor.R
import com.instagram.photoediting.ColorPickerAdapter.OnColorPickerClickListener

class TextEditorDialogFragment : DialogFragment() {
    private var mAddTextEditText: EditText? = null
    private var imgItalic: ImageView? = null
    private var mAddTextDoneTextView: TextView? = null
    private var mInputMethodManager: InputMethodManager? = null
    private var mColorCode = 0
    private var mFont: Typeface? = null
    private var mTextEditorListener: TextEditorListener? = null

    interface TextEditorListener {
        fun onDone(inputText: String?, colorCode: Int, font: Typeface)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_text_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAddTextEditText = view.findViewById(R.id.add_text_edit_text)
        mInputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mAddTextDoneTextView = view.findViewById(R.id.add_text_done_tv)
//        imgItalic = view.findViewById(R.id.img_italic)
        val addTextColorPickerRecyclerView: RecyclerView =
            view.findViewById(R.id.add_text_color_picker_recycler_view)
        val addFontPickerRecyclerView: RecyclerView =
            view.findViewById(R.id.add_text_font_picker_recycler_view)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager1 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        mAddTextEditText?.requestFocus()

        addTextColorPickerRecyclerView.layoutManager = layoutManager
        addFontPickerRecyclerView.layoutManager = layoutManager1

        addTextColorPickerRecyclerView.setHasFixedSize(true)
        addFontPickerRecyclerView.setHasFixedSize(true)

        val colorPickerAdapter = ColorPickerAdapter(requireActivity())
        val fontPickerAdapter = FontPickerAdapter(requireActivity())


        colorPickerAdapter.setOnColorPickerClickListener(object : OnColorPickerClickListener {
            override fun onColorPickerClickListener(colorCode: Int) {
                mColorCode = colorCode
                mAddTextEditText!!.setTextColor(colorCode)
            }
        })

        fontPickerAdapter.setOnFontClickListener(object :
            FontPickerAdapter.OnFontPickerClickListener {
            override fun onFontClickListener(code: Typeface) {
                mFont = code
                mAddTextEditText!!.typeface = code
            }
        })

        addTextColorPickerRecyclerView.adapter = colorPickerAdapter
        addFontPickerRecyclerView.adapter = fontPickerAdapter

        mAddTextEditText!!.setText(requireArguments().getString(EXTRA_INPUT_TEXT))
        mColorCode = requireArguments().getInt(EXTRA_COLOR_CODE)
//            mAddTextEditText!!.typeface = typeface
//        mFont = requireArguments().getBundle(EXTRA_FONT_CODE)
        mAddTextEditText!!.setTextColor(mColorCode)
        mInputMethodManager!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

        //Make a callback on activity when user is done with text editing
        mAddTextDoneTextView!!.setOnClickListener { onClickListenerView ->
            mInputMethodManager!!.hideSoftInputFromWindow(onClickListenerView.windowToken, 0)
            dismiss()
            val inputText = mAddTextEditText!!.text.toString()
            if (!TextUtils.isEmpty(inputText) && mTextEditorListener != null) {
                mFont?.let { mTextEditorListener!!.onDone(inputText, mColorCode, it) }
            }

        }
//        imgItalic!!.setOnClickListener {
//            val typeface = ResourcesCompat.getFont(requireContext(),R.font.arabics)
//            mAddTextEditText!!.typeface = typeface
//        }
    }

    //Callback to listener if user is done with text editing
    fun setOnTextEditorListener(textEditorListener: TextEditorListener) {
        mTextEditorListener = textEditorListener
    }

    companion object {
        private val TAG: String = TextEditorDialogFragment::class.java.simpleName
        const val EXTRA_INPUT_TEXT = "extra_input_text"
        const val EXTRA_COLOR_CODE = "extra_color_code"

        //Show dialog with provide text and text color
        //Show dialog with default text input as empty and text color white
        @JvmOverloads
        fun show(
            appCompatActivity: AppCompatActivity,
            inputText: String = "",
            @ColorInt colorCode: Int = ContextCompat.getColor(appCompatActivity, R.color.white),
            font: String = ""
        ): TextEditorDialogFragment {
            val args = Bundle()
            args.putString(EXTRA_INPUT_TEXT, inputText)
            args.putInt(EXTRA_COLOR_CODE, colorCode)
            val fragment = TextEditorDialogFragment()
            fragment.arguments = args
            fragment.show(appCompatActivity.supportFragmentManager, TAG)
            return fragment
        }
    }
}