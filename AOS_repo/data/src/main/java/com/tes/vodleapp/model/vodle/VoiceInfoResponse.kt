package com.tes.vodleapp.model.vodle

import com.google.gson.annotations.SerializedName

data class VoiceInfoResponse(
    val status: Int,
    val message: String,
    val data: List<VoiceInfo>
)

data class VoiceInfo(
    val voiceType: String,
    @SerializedName("url")val sampleUrl: String,
    val voiceTypeKr: String
)
