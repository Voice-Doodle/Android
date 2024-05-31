package com.tes.presentation.main.recording

import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import com.tes.presentation.main.MainViewEvent
import com.tes.presentation.main.MainViewModel
import com.tes.presentation.main.MainViewState
import com.tes.presentation.model.VodleOption

@OptIn(UnstableApi::class)
@Composable
internal fun IntroDuctionDialog(
    viewModel: MainViewModel,
    viewState: MainViewState.MakingVodle,
    player: ExoPlayer,
) {
    val context = LocalContext.current
    val selectedVoiceIndex = remember { mutableIntStateOf(0) }
    val selectedGenderState = remember { mutableStateOf(viewState.gender) }
    val dataSourceFactory = DefaultDataSource.Factory(context)

    when (viewState.vodleOption) {
        VodleOption.TEXT -> {
            TextIntroductionView(
                viewModel,
                selectedVoiceIndex,
                viewState,
                player
            )
        }

        VodleOption.VOICE -> {
            VoiceIntroductionView(
                viewModel,
                selectedGenderState,
                selectedVoiceIndex,
                viewState,
                context,
                player
            )
        }
    }

    LaunchedEffect(selectedVoiceIndex.intValue) {
        viewModel.onTriggerEvent(
            MainViewEvent.OnSelectVoiceType(viewState.voiceInfoList[selectedVoiceIndex.intValue].voiceType)
        )
    }

    LaunchedEffect(viewState.selectedVoiceType) {
        when (viewState.selectedVoiceType) {
            "original" -> {
                player.stop()
            }

            else -> {
                player.stop()
                val streamingUrl = viewState.voiceInfoList.find { it.voiceType == viewState.selectedVoiceType}?.sampleUrl?: ""
                val hlsMediaSource =
                    HlsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(
                            MediaItem.fromUri(
                                streamingUrl
                            )
                        )
                player.setMediaSource(hlsMediaSource)
                player.prepare()
                player.play()
            }
        }
    }

    LaunchedEffect(selectedGenderState.value) {
        viewModel.onTriggerEvent(
            MainViewEvent.OnSelectVoiceType(viewState.voiceInfoList[selectedVoiceIndex.intValue].voiceType)
        )
    }
}
