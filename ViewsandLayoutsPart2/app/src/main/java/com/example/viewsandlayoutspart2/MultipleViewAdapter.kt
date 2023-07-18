package com.example.viewsandlayoutspart2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.viewsandlayoutspart2.databinding.ImageRecyclerViewItemBinding
import com.example.viewsandlayoutspart2.databinding.ImagetextRecyclerViewItemBinding
import com.example.viewsandlayoutspart2.databinding.TextRecyclerViewItemBinding

private const val TEXT_ITEM_TYPE = 0
private const val IMAGE_ITEM_TYPE = 1
private const val CARD_ITEM_TYPE = 2

class MultipleViewAdapter(private val data: List<Any>) : RecyclerView.Adapter<MultipleViewAdapter.BaseViewHolder<*>>() {

    abstract inner class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    inner class TextViewHolder(private val binding: TextRecyclerViewItemBinding) : BaseViewHolder<String>(binding.root) {
        override fun bind(item: String) {
            binding.textViewTextItem.text = item
        }
    }

    inner class ImageViewHolder(private val binding: ImageRecyclerViewItemBinding) : BaseViewHolder<Int>(binding.root) {
        override fun bind(@DrawableRes item: Int) {
            binding.imageViewImageItem.setImageResource(item)
        }
    }

    inner class ImageTextViewHolder(private val binding: ImagetextRecyclerViewItemBinding) : BaseViewHolder<ImageTextData>(binding.root) {
        override fun bind(item: ImageTextData) {
            binding.imageViewCardItem.setImageResource(item.imageId)
            binding.textViewCardItem.text = item.text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TEXT_ITEM_TYPE -> TextViewHolder(TextRecyclerViewItemBinding.inflate(inflater, parent, false))
            IMAGE_ITEM_TYPE -> ImageViewHolder(ImageRecyclerViewItemBinding.inflate(inflater, parent, false))
            CARD_ITEM_TYPE -> ImageTextViewHolder(ImagetextRecyclerViewItemBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = data[position]
        when(holder) {
            is TextViewHolder -> holder.bind(item as String)
            is ImageViewHolder -> holder.bind(item as Int)
            is ImageTextViewHolder -> holder.bind(item as ImageTextData)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(data[position]) {
            is String -> TEXT_ITEM_TYPE
            is Int -> IMAGE_ITEM_TYPE
            is ImageTextData -> CARD_ITEM_TYPE
            else -> throw IllegalArgumentException("This type of data isn't supported ${data}")
        }

    override fun getItemCount(): Int = data.size

    companion object {
        val defaultData: List<Any>
            get() {
                val texts = listOf("Text 1", "Text 2", "Text 3")
                val imageIds = listOf(R.drawable.one, R.drawable.two, R.drawable.three)
                    //listOf(R.drawable.baseline_pages_24, R.drawable.baseline_web_24, R.drawable.baseline_recycling_24)
                return listOf<Any>(*texts.toTypedArray(), *imageIds.toTypedArray(),
                    *(imageIds zip texts).map { ImageTextData(it.first, it.second) }.toTypedArray()).shuffled()
            }
    }
}

data class ImageTextData(
    @DrawableRes
    val imageId: Int,
    val text: String
)