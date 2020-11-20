package dev.gvetri.newsfeed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.gvetri.newsfeed.databinding.NewsFeedFragmentBinding
import dev.gvetri.model.PaginatedVideoList
import dev.gvetri.shared.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class NewsFeedFragment : Fragment(R.layout.news_feed_fragment) {

    private val viewModel: NewsFeedViewModel by inject()
    private val binding: NewsFeedFragmentBinding? by viewBinding(NewsFeedFragmentBinding::bind)

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

    private fun onDataLoaded(featureState: NewsFeedState) {
        when (featureState) {
            is NewsFeedState.Success -> loadVideoList(featureState.result)
            is NewsFeedState.Error -> {
                toggleProgressbar(false)
                showToastError(getString(R.string.request_error))
            }
            NewsFeedState.Loading -> toggleProgressbar(true)
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