package com.example.dicodingevent.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmissionfundamental_1.ui.EventAdapter
import com.example.dicodingevent.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var upcomingEventAdapter: EventAdapter
    private lateinit var finishedEventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi adapter untuk event yang akan datang
        upcomingEventAdapter = EventAdapter(isUpcoming = true)
        binding.rvUpcomingEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = upcomingEventAdapter
        }

        binding.rvUpcomingEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingEvents.adapter = upcomingEventAdapter

        // Inisialisasi adapter untuk event yang sudah selesai
        finishedEventAdapter = EventAdapter(isUpcoming = false)
        binding.rvFinishedEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = finishedEventAdapter
        }

        binding.rvFinishedEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFinishedEvents.adapter = finishedEventAdapter

        // Observe data dari ViewModel
        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE // Atur visibilitas ProgressBar
        }

        homeViewModel.upcomingEvents.observe(viewLifecycleOwner) { events ->
            upcomingEventAdapter.setData(events)
        }

        homeViewModel.finishedEvents.observe(viewLifecycleOwner) { events ->
            finishedEventAdapter.setData(events)
        }

        homeViewModel.loadEvents() // Memanggil fungsi untuk load events
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
