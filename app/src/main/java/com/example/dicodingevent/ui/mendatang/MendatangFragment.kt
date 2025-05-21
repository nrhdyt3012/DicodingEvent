package com.example.dicodingevent.ui.mendatang
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmissionfundamental_1.ui.EventAdapter
import com.example.dicodingevent.databinding.FragmentMendatangBinding



class MendatangFragment : Fragment() {

    private var _binding: FragmentMendatangBinding? = null
    private val binding get() = _binding!!
    private val mendatangViewModel: MendatangViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMendatangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        eventAdapter = EventAdapter(isUpcoming = true)
        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }

        // Observe data dari ViewModel
        mendatangViewModel.events.observe(viewLifecycleOwner) { events ->
            eventAdapter.setData(events)
        }

        mendatangViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        mendatangViewModel.loadEvents() // Memanggil fungsi untuk load events
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
