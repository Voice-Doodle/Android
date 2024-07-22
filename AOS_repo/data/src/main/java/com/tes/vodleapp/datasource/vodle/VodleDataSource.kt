package com.tes.vodleapp.datasource.vodle

import com.tes.domain.model.Gender
import com.tes.domain.model.Location
import com.tes.vodleapp.model.BasicResponse
import com.tes.vodleapp.model.vodle.ConversionResponse
import com.tes.vodleapp.model.vodle.VodlesAroundResponse
import com.tes.vodleapp.model.vodle.VoiceInfoResponse
import java.io.File

interface VodleDataSource {

    suspend fun fetchVodlesAround(
        centerLocation: Location,
        northEastLocation: Location,
        southWestLocation: Location
    ): Result<VodlesAroundResponse>

    suspend fun uploadVodle(
        recordingFile: File,
        writer: String,
        recordType: String,
        streamingUrl: String,
        location: Location
    ): Result<BasicResponse>

    suspend fun convertVoice(
        recordingFile: File,
        selectedVoice: String,
        gender: Gender
    ): Result<ConversionResponse>

    suspend fun convertTTS(
        content: String,
        selectedVoice: String
    ): Result<ConversionResponse>

    suspend fun fetchVoiceInfo(): Result<VoiceInfoResponse>
}
