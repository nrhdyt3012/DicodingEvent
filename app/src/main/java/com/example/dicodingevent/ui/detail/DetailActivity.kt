package com.example.dicodingevent.ui.detail
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.dicodingevent.R
import com.example.dicodingevent.data.local.EventDatabase
import com.example.dicodingevent.data.local.EventEntity
import com.example.dicodingevent.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var database: EventDatabase
    private var isFavorite: Boolean = false
    private var eventId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        eventId = intent.getIntExtra(EXTRA_EVENT_ID, 0)
        val eventTitle = intent.getStringExtra(EXTRA_EVENT_TITLE) ?: "No Title"
        val eventDescription = intent.getStringExtra(EXTRA_EVENT_DESCRIPTION) ?: "No Description"
        val eventOwner = intent.getStringExtra(EXTRA_EVENT_OWNER) ?: "Unknown"
        val eventTime = intent.getStringExtra(EXTRA_EVENT_BEGIN_TIME) ?: "Unknown Time"
        val eventQuota = intent.getIntExtra(EXTRA_EVENT_QUOTA, 0)
        val eventImage = intent.getStringExtra(EXTRA_EVENT_IMAGE) ?: ""
        val registerUrl = intent.getStringExtra(EXTRA_EVENT_REGISTER_URL) ?: ""

        binding.tvEventName.text = eventTitle
        binding.tvOwnerName.text = getString(R.string.tv_owner_name, eventOwner)
        binding.tvBeginTime.text = getString(R.string.tv_begin_time, eventTime)
        binding.tvQuota.text = getString(R.string.tv_quota, eventQuota)

        Glide.with(this)
            .load(eventImage)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(binding.ivEventImage)

        binding.tvEventDescription.text = HtmlCompat.fromHtml(eventDescription, HtmlCompat.FROM_HTML_MODE_LEGACY)

        database = EventDatabase.getDatabase(this)

        checkIfFavorite(eventId)

        binding.fabFavorite.setOnClickListener {
            toggleFavorite(eventId,
                eventTitle,
                eventDescription,
                eventOwner,
                eventTime,
                registerUrl,
                eventQuota,
                eventImage)
        }

        binding.btnRegister.setOnClickListener {
            if (registerUrl.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(registerUrl))
                startActivity(intent)
            } else {
                Toast.makeText(this, "URL not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkIfFavorite(eventId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val event = database.eventDao().getEventById(eventId)
            isFavorite = event != null
            runOnUiThread {
                updateFavoriteIcon(isFavorite)
            }
        }
    }

    private fun toggleFavorite( eventId: Int,
                                eventTitle: String,
                                eventDescription: String,
                                eventOwner: String,
                                eventTime: String,
                                registerUrl: String,
                                eventQuota: Int,
                                eventImage: String
                                ) {
        CoroutineScope(Dispatchers.IO).launch {
            val event = database.eventDao().getEventById(eventId)
            if (event == null) {
                val favoriteEvent =
                    EventEntity(
                        eventId,
                        eventTitle,
                        eventOwner,
                        eventTime,
                        eventQuota,
                        registrants = "",
                        eventDescription,
                        eventImage,
                        registerUrl
                    )
                database.eventDao().insert(favoriteEvent)
                runOnUiThread {
                    Toast.makeText(
                        this@DetailActivity,
                        "Event added to favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateFavoriteIcon(true)
                    setResult(RESULT_FAVORITE_UPDATED)
                }
            } else {

                database.eventDao().deleteById(eventId)
                runOnUiThread {
                    Toast.makeText(this@DetailActivity, "Event removed from favorites", Toast.LENGTH_SHORT).show()
                    updateFavoriteIcon(false)
                    setResult(RESULT_FAVORITE_UPDATED)
                }
            }
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_EVENT_ID = "event_id"
        const val EXTRA_EVENT_TITLE = "event_title"
        const val EXTRA_EVENT_DESCRIPTION = "event_description"
        const val EXTRA_EVENT_OWNER = "event_owner"
        const val EXTRA_EVENT_BEGIN_TIME = "event_begin_time"
        const val EXTRA_EVENT_QUOTA = "event_quota"
        const val EXTRA_EVENT_IMAGE = "event_image"
        const val EXTRA_EVENT_REGISTER_URL = "event_register_url"
        const val RESULT_FAVORITE_UPDATED = 1
    }
}

