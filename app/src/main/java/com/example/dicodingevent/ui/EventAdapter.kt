package com.dicoding.mysubmissionfundamental_1.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingevent.R
import com.example.dicodingevent.data.remote.response.ListEventsItem
import com.example.dicodingevent.ui.detail.DetailActivity

class EventAdapter(private val isUpcoming: Boolean) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private val events = ArrayList<ListEventsItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newEvents: List<ListEventsItem>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventName: TextView = view.findViewById(R.id.tvEventName)
        val eventImage: ImageView = view.findViewById(R.id.ivEventImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.name

        Glide.with(holder.itemView.context)
            .load(event.mediaCover)
            .into(holder.eventImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra(DetailActivity.EXTRA_EVENT_ID, event.id)
            intent.putExtra(DetailActivity.EXTRA_EVENT_TITLE, event.name)
            intent.putExtra(DetailActivity.EXTRA_EVENT_DESCRIPTION, event.description)
            intent.putExtra(DetailActivity.EXTRA_EVENT_OWNER, event.ownerName)
            intent.putExtra(DetailActivity.EXTRA_EVENT_BEGIN_TIME, event.beginTime)
            intent.putExtra(DetailActivity.EXTRA_EVENT_QUOTA, (event.quota ?: 0) - (event.registrants ?: 0))
            intent.putExtra(DetailActivity.EXTRA_EVENT_IMAGE, event.mediaCover)
            intent.putExtra(DetailActivity.EXTRA_EVENT_REGISTER_URL, event.link)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = events.size
}
