package dev.gvetri.featurea

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import dev.gvetri.featurea.databinding.FeatureAFragmentBinding
import dev.gvetri.shared.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class FeatureAFragment : Fragment(R.layout.feature_a_fragment) {

    private val viewModel: FeatureAViewModel by inject()
    private val binding: FeatureAFragmentBinding? by viewBinding(FeatureAFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.stateDataFlow.collect(::onDataLoaded)
        }
    }


    private fun onDataLoaded(featureState: FeatureState) {
        when (featureState) {
            is FeatureState.Success -> binding?.image?.apply { loadImage(featureState) }
            is FeatureState.Error -> {
                toggleProgressbar(false)
                showToastError(getString(R.string.request_error))
            }
            FeatureState.Loading -> toggleProgressbar(true)
        }
    }

    private fun ImageView.loadImage(featureState: FeatureState.Success) {
        load(featureState.result.url) {
            listener(object : ImageRequest.Listener {

                override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
                    toggleProgressbar(false)
                }

                override fun onError(request: ImageRequest, throwable: Throwable) {
                    toggleProgressbar(false)
                    showToastError(context.getString(R.string.image_load_error))
                }
            })
        }
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