package com.instagram.photoediting

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.instagram.imageeditor.R

class FontPickerAdapter internal constructor(
    private var context: Context,
    fontPicker: List<Typeface>,
) : RecyclerView.Adapter<FontPickerAdapter.ViewHolder>() {
    private var inflater: LayoutInflater
    private val fontPicker: List<Typeface>
    private var onFontClickListener: OnFontPickerClickListener? = null

    internal constructor(context: Context) : this(context, getDefaultFonts(context)) {
        this.context = context
        inflater = LayoutInflater.from(context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.font_picker_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.colorPickerView.setBackgroundColor(colorPickerColors[position])
    }


    override fun getItemCount(): Int {
        return fontPicker.size
    }

    fun setOnFontClickListener(onFontClickListener: OnFontPickerClickListener?) {
        this.onFontClickListener = onFontClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var fontPickerView: View = itemView.findViewById(R.id.fontPickerView)

        init {
            itemView.setOnClickListener {
                if (onFontClickListener != null) onFontClickListener!!.onFontClickListener(
                    fontPicker[adapterPosition]
                )
            }
        }
    }

    interface OnFontPickerClickListener {
        fun onFontClickListener(code: Typeface)
    }

    companion object {
        private fun getDefaultFonts(context: Context): List<Typeface> {

            val fonts = ArrayList<Typeface>()
            fonts.add(ResourcesCompat.getFont(context, R.font.arabics)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.aria)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.arshia)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.badkonak)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.badr)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.baran)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.bardia)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.cheshmeh)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.nastaliq)!!)
            fonts.add(ResourcesCompat.getFont(context, R.font.shekasteh)!!)

            return fonts
        }
    }

    init {
        inflater = LayoutInflater.from(context)
        this.fontPicker = fontPicker
    }
}