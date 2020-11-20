package dev.gvetri.newsfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.gvetri.newsfeed.databinding.LayoutVideoItemBinding
import dev.gvetri.model.VideoItem

class VideoListAdapter(private val onItemClicked: (VideoItem) -> Unit) :
    RecyclerView.Adapter<ItemListViewHolder>() {

    private val nasaItemList: MutableList<VideoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder =
        ItemListViewHolder(
            LayoutVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind(nasaItemList[position], onItemClicked)
    }

    override fun getItemCount(): Int = nasaItemList.size

    fun submitList(list: List<VideoItem>) {
        nasaItemList.clear()
        nasaItemList.addAll(list)
        notifyDataSetChanged()
    }
}
