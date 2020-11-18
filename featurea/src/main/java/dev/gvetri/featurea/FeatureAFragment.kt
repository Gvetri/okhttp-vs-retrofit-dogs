package dev.gvetri.featurea

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import dev.gvetri.featurea.databinding.FeatureAFragmentBinding
import dev.gvetri.model.PaginatedVideoList
import dev.gvetri.shared.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class FeatureAFragment : Fragment(R.layout.feature_a_fragment) {

    private val viewModel: FeatureAViewModel by inject()
    private val binding: FeatureAFragmentBinding? by viewBinding(FeatureAFragmentBinding::bind)

    private val videoListAdapter = VideoListAdapter { videoItem ->
        val uri = "client://playerFragment/${videoItem.id}".toUri()
        findNavController().navigate(uri)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.stateDataFlow.collect(::onDataLoaded)
        }
        setupUI()
    }

    private fun setupUI() {
        binding?.apply {
            with(videoList) {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = videoListAdapter
            }
        }
    }

    private fun onDataLoaded(featureState: FeatureState) {
        when (featureState) {
            is FeatureState.Success -> loadVideoList(featureState.result)
            is FeatureState.Error -> {
                toggleProgressbar(false)
                showToastError(getString(R.string.request_error))
            }
            FeatureState.Loading -> toggleProgressbar(true)
        }
    }

    private fun loadVideoList(result: PaginatedVideoList) {
        toggleProgressbar(false)
        videoListAdapter.submitList(result.list)
    }

    private fun showToastError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun toggleProgressbar(show: Boolean) {
        binding?.apply {
            if (show) {
                progressCircular.visibility = View.VISIBLE
            } else progressCircular.visibility = View.GONE
        }
    }

}