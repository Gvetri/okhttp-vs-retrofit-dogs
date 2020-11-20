package dev.gvetri.newsfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.gvetri.newsfeed.databinding.LayoutVideoItemBinding
import dev.gvetri.model.VideoItem

class VideoListAdapter(private val onItemClicked: (VideoItem) -> Unit) :
    RecyclerView.Adapter<ItemListViewHolder>() {

    private val videoItemList: MutableList<VideoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder =
        ItemListViewHolder(
            LayoutVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind(videoItemList[position], onItemClicked)
    }

    override fun getItemCount(): Int = videoItemList.size

    fun submitList(list: List<VideoItem>) {
        videoItemList.clear()
        videoItemList.addAll(list)
        notifyDataSetChanged()
    }
}
