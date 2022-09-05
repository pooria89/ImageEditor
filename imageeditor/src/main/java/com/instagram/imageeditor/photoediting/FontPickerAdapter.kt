package com.instagram.imageeditor.photoediting

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.instagram.imageeditor.R


class FontPickerAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<FontPickerAdapter.ViewHolder>() {

    private var onFontClickListener: OnFontPickerClickListener? = null
    private val fonts = listOf(
        Pair(ResourcesCompat.getFont(context, R.font.arabics)!!, R.drawable.arabics),
        Pair(ResourcesCompat.getFont(context, R.font.aria)!!, R.drawable.aria),
        Pair(ResourcesCompat.getFont(context, R.font.arshia)!!, R.drawable.arshia),
        Pair(ResourcesCompat.getFont(context, R.font.nastaliq)!!, R.drawable.nastaliq),
        Pair(ResourcesCompat.getFont(context, R.font.badr)!!, R.drawable.badr),
        Pair(ResourcesCompat.getFont(context, R.font.baran)!!, R.drawable.baran),
        Pair(ResourcesCompat.getFont(context, R.font.bardia)!!, R.drawable.bardia),
        Pair(ResourcesCompat.getFont(context, R.font.cheshmeh)!!, R.drawable.cheshmeh),
        Pair(ResourcesCompat.getFont(context, R.font.shekasteh)!!, R.drawable.shekasteh),
        Pair(ResourcesCompat.getFont(context, R.font.shabnam)!!, R.drawable.shabnam),
        Pair(ResourcesCompat.getFont(context, R.font.iransans)!!, R.drawable.iransans),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.font_picker_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fontPickerView.setImageResource(fonts[position].second)
    }


    override fun getItemCount(): Int {
        return fonts.size
    }

    fun setOnFontClickListener(onFontClickListener: OnFontPickerClickListener?) {
        this.onFontClickListener = onFontClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fontPickerView: ImageView = itemView.findViewById(R.id.img_font)

        init {
            itemView.setOnClickListener {
                onFontClickListener?.onFontClickListener(fonts[adapterPosition].first)
            }
        }
    }

    interface OnFontPickerClickListener {
        fun onFontClickListener(code: Typeface)
    }

}
