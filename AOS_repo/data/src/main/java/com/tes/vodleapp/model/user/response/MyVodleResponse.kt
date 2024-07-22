package com.tes.vodleapp.model.user.response

import com.google.gson.annotations.SerializedName
import com.tes.vodleapp.model.vodle.VodleResponse

data class MyVodleResponse(
    val status: Int,
    val message: String,
    @SerializedName("data") val dataBody: List<VodleResponse>
)
