package com.example.dicodingevent.ui.favorite
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.data.local.EventDatabase
import com.example.dicodingevent.data.local.EventEntity
import com.example.dicodingevent.databinding.FragmentFavoriteBinding
import com.example.dicodingevent.ui.detail.DetailActivity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteFragment : Fragment(), FavoriteViewModel.OnItemClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var database: EventDatabase
    private val detailActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == DetailActivity.RESULT_FAVORITE_UPDATED) {
            loadFavoriteEvents()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = FavoriteViewModel(this)
        binding.rvFavoriteEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteViewModel
        }

        database = EventDatabase.getDatabase(requireContext())

        loadFavoriteEvents()
    }
    private fun loadFavoriteEvents() {
        lifecycleScope.launch {
            val favoriteEvents = withContext(Dispatchers.IO) {
                database.eventDao().getAllFavoriteEvents()
            }
            favoriteViewModel.submitList(favoriteEvents)
        }
    }
    override fun onItemClick(event: EventEntity) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_EVENT_ID, event.id)
            putExtra(DetailActivity.EXTRA_EVENT_TITLE, event.name)
            putExtra(DetailActivity.EXTRA_EVENT_DESCRIPTION, event.description)
            putExtra(DetailActivity.EXTRA_EVENT_OWNER, event.ownerName)
            putExtra(DetailActivity.EXTRA_EVENT_BEGIN_TIME, event.beginTime)
            putExtra(DetailActivity.EXTRA_EVENT_QUOTA, event.quota)
            putExtra(DetailActivity.EXTRA_EVENT_IMAGE, event.mediaCover)
            putExtra(DetailActivity.EXTRA_EVENT_REGISTER_URL, event.link)
        }
        detailActivityLauncher.launch(intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
