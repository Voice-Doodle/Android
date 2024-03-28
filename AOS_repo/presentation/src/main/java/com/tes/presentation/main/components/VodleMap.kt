package com.tes.presentation.main.components

import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapEffect
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.tes.presentation.R
import com.tes.presentation.main.MainViewEvent
import com.tes.presentation.main.MainViewModel
import com.tes.presentation.model.Vodle
import ted.gun0912.clustering.naver.TedNaverClustering

private const val TAG = "VodleMap_싸피"

@OptIn(ExperimentalNaverMapApi::class)
@Composable
internal fun VodleMap(
    viewModel: MainViewModel,
    vodleList: List<Vodle>,
    cameraPositionState: CameraPositionState
) {
    NaverMap(
        modifier = Modifier.fillMaxSize(),
        locationSource = rememberFusedLocationSource(),
        cameraPositionState = cameraPositionState,
        contentPadding = PaddingValues(48.dp),
        properties = MapProperties(
            locationTrackingMode = LocationTrackingMode.Face
        ),
        uiSettings = MapUiSettings(
            isLocationButtonEnabled = false,
            isZoomControlEnabled = false,
            isCompassEnabled = false

        )
    ) {
        val context = LocalContext.current
        var clusterManager by remember { mutableStateOf<TedNaverClustering<Vodle>?>(null) }
        MapEffect(vodleList) { map ->
            if (clusterManager == null) {
                clusterManager = TedNaverClustering.with<Vodle>(context, map)
                    .customMarker {
                        Marker().apply {
                            icon = OverlayImage.fromResource(R.drawable.megaphone)
                            width = 120
                            height = 120
                        }
                    }
                    .customCluster {
                        ImageView(context).apply {
                            setImageResource(R.drawable.resized_megaphone)
                        }
                    }
                    .markerClickListener {
                        Log.d(TAG, "VodleMap: 마커 위치 : ${it.location}")
                        viewModel.onTriggerEvent(MainViewEvent.OnClickMarker(it.location))
                    }
                    .clusterAnimation(true)
                    .clickToCenter(true)
                    .make()
            }
            clusterManager?.addItems(vodleList)
        }
    }
}
