package dev.gvetri.player

import android.content.pm.ActivityInfo
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.OrientationEventListener
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dailymotion.android.player.sdk.events.*
import dev.gvetri.model.VideoItem
import dev.gvetri.player.Orientation.*
import dev.gvetri.player.databinding.PlayerFragmentBinding
import dev.gvetri.shared.toggle
import dev.gvetri.shared.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

private const val VIDEOKEY = "video"
private const val VIDEO_ID_KEY = "id"

class PlayerFragment : Fragment(R.layout.player_fragment) {

    private val viewModel: PlayerViewModel by inject()
    private val binding: PlayerFragmentBinding? by viewBinding(PlayerFragmentBinding::bind)

    private val portraitOrientationRange = 0..45
    private val landscapeOrientationRange = 46..315

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeFlow()
        setupUI()
        initializeOrientationListener()
    }

    private fun initializeFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.stateDataFlow.collect(::onStateChanged)
        }
    }

    private fun onStateChanged(state: VideoPlayerUiState) {
        when (state) {
            is VideoPlayerUiState.PlayVideo -> startVideo(state.id)
            VideoPlayerUiState.VideoIdNotValid -> showToastError(getString(R.string.error_video_string))
            VideoPlayerUiState.Loading -> binding?.progressCircular?.toggle(true)
            VideoPlayerUiState.VideoDetailError -> showToastError(getString(R.string.video_detail_error_loading))
            is VideoPlayerUiState.VideoDetailSuccess -> setVideoDetailInfo(state.videoItem)
            is VideoPlayerUiState.OrientationChanged -> updateVideoPlayerSize(state.orientation)
            VideoPlayerUiState.FullScreenMode -> setPlayerFullScreen()
        }
    }

    private fun setPlayerFullScreen() {
        val orientation = resources.configuration.orientation
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            viewModel.orientationChanged(LANDSCAPE)
        } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            viewModel.orientationChanged(PORTRAIT)
        }
    }

    private fun updateVideoPlayerSize(orientation: Orientation) {
        when (orientation) {
            PORTRAIT -> {
                changeOrientation(orientation)
                setVideoPlayerPortrait()
            }
            LANDSCAPE -> {
                changeOrientation(orientation)
                setVideoPlayerLandscape()
            }
        }
    }

    private fun changeOrientation(orientation: Orientation) {
        when (orientation) {
            PORTRAIT -> {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            LANDSCAPE -> {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }

    private fun setVideoPlayerLandscape() {
        binding?.apply {
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            videoplayer.setFullScreen(root)
        }
    }

    private fun setVideoPlayerPortrait() {
        binding?.apply {
            videoTitle?.let {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                videoplayer.setPortraitMode(root, it)
            }
        }
    }

    private fun setVideoDetailInfo(videoItem: VideoItem) {
        binding?.apply {
            videoTitle?.apply {
                text = videoItem.title
            }
        }
    }

    private fun startVideo(id: String) {
        binding?.progressCircular?.toggle(false)
        binding?.videoplayer?.load(params = mapOf(VIDEOKEY to id))
    }

    private fun setupUI() {
        binding?.videoplayer?.setEventListener(::onPlayerEvent)
        viewModel.loadVideo(arguments?.getString(VIDEO_ID_KEY))
    }

    private fun onPlayerEvent(playerEvent: PlayerEvent) {
        if (playerEvent is FullScreenToggleRequestedEvent) {
            viewModel.fullScreenToggle()
        }
    }

    private fun showToastError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun initializeOrientationListener() {
        object : OrientationEventListener(context) {
            override fun onOrientationChanged(orientation: Int) {
                when (orientation) {
                    in portraitOrientationRange -> {
                        viewModel.orientationChanged(Orientation.from(ORIENTATION_PORTRAIT))
                    }
                    in landscapeOrientationRange -> {
                        viewModel.orientationChanged(Orientation.from(ORIENTATION_LANDSCAPE))
                    }
                }
            }
        }.enable()
    }

    override fun onPause() {
        super.onPause()
        binding?.videoplayer?.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding?.videoplayer?.onResume()
    }

}