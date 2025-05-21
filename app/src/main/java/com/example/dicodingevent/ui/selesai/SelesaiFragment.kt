package com.example.dicodingevent.ui.selesai
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmissionfundamental_1.ui.EventAdapter
import com.example.dicodingevent.databinding.FragmentSelesaiBinding
import com.example.dicodingevent.ui.SelesaiViewModel


class SelesaiFragment : Fragment() {

    private var _binding: FragmentSelesaiBinding? = null
    private val binding get() = _binding!!
    private val selesaiViewModel: SelesaiViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelesaiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventAdapter = EventAdapter(isUpcoming = false)
        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }

        // Observe data dari ViewModel
        selesaiViewModel.events.observe(viewLifecycleOwner) { events ->
            eventAdapter.setData(events)
        }

        selesaiViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        selesaiViewModel.loadEvents() // Call a function to load doe events
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
