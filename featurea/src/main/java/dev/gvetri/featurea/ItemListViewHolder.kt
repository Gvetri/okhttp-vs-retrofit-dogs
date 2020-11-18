package dev.gvetri.featurea

import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.gvetri.featurea.databinding.LayoutVideoItemBinding
import dev.gvetri.model.VideoItem

class ItemListViewHolder(private val binding: LayoutVideoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(videoItem: VideoItem, onItemClicked: (VideoItem) -> Unit) {
        binding.apply {
            itemTitle.text = videoItem.title
            itemDescription.text = videoItem.channel
            cardItemContainer.setOnClickListener { onItemClicked(videoItem) }
            videoItem.thumbnailUrl?.apply { previewImage.load(this) }
        }
    }
}
