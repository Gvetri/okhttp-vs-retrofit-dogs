package dev.gvetri.player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dailymotion.android.player.sdk.events.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeFlow()
        setupUI()
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
        }
    }

    private fun startVideo(id: String) {
        binding?.progressCircular?.toggle(false)
        binding?.videoplayer?.apply {
            load(params = mapOf(VIDEOKEY to id))
            setEventListener(::onPlayerEvent)
        }
    }

    private fun setupUI() {
        viewModel.loadVideo(arguments?.getString(VIDEO_ID_KEY))
    }

    private fun onPlayerEvent(playerEvent: PlayerEvent) {
        if (playerEvent is FullScreenToggleRequestedEvent) {
            Toast.makeText(
                context,
                "Fullscreen",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showToastError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
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