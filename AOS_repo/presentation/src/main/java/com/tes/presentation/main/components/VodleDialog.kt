package com.tes.presentation.main.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tes.presentation.main.MainViewState
import com.tes.presentation.model.Vodle
import main.components.VodleListDialogComponent

private const val TAG = "VodleDialog_싸피"
@Composable
internal fun VodleDialog(
    viewState: MainViewState,
//    vodleList: List<Vodle>,
    modifier: Modifier
) {
    var markerVodleList : MutableList<Vodle> = mutableListOf()

    for(i in 0..viewState.vodleList.size-1){
        //위도 값은 -90 ~ 90 사이의 값, 경도 값은 -180 ~ 180 사이의 값이어야 함(이외의 값은 지도 상으로 표시 x)
        Log.d(TAG, "VodleDialog: vodle값 : ${viewState.vodleList.get(i)}")
        if(viewState.location == viewState.vodleList.get(i).location){
            markerVodleList.add(viewState.vodleList.get(i))
        }
    }
    VodleListDialogComponent(markerVodleList, {})
}
