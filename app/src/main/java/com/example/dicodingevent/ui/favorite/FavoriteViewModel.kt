package com.example.dicodingevent.ui.favorite

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.local.EventEntity
import com.example.dicodingevent.databinding.ItemFavoriteEventBinding



class FavoriteViewModel(private val listener: FavoriteFragment) : RecyclerView.Adapter<FavoriteViewModel.FavoriteViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(event: EventEntity)
    }

    private val favoriteEvents = mutableListOf<EventEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(events: List<EventEntity>) {
        favoriteEvents.clear()
        favoriteEvents.addAll(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteEvents[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(favoriteEvents[position])
        }
    }

    override fun getItemCount(): Int = favoriteEvents.size

    class FavoriteViewHolder(private val binding: ItemFavoriteEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity) {
            binding.tvEventTitle.text = event.name

            Glide.with(binding.root.context)
                .load(event.mediaCover)
                .into(binding.ivEventImage)

            Log.d("FavoriteAdapter", "Loaded image URL: ${event.mediaCover}")
        }
    }
}
