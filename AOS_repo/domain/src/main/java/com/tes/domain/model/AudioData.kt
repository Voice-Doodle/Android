package com.tes.domain.model

data class VoiceInfo(
    val voiceType: String,
    val sampleUrl: String,
    val voiceTypeKr: String
)

enum class Gender(val value: String) {
    Male("male"), Female("female")
}

data class AudioData(
    val voiceType: String,
    val convertedAudioUrl: Url
)

typealias Url = String
