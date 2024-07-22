package com.tes.vodleapp.datasource.vodle

import com.tes.domain.model.Gender
import com.tes.domain.model.Location
import com.tes.domain.model.lat
import com.tes.domain.model.lng
import com.tes.vodleapp.api.VodleMetaData
import com.tes.vodleapp.api.VodleService
import com.tes.vodleapp.model.BasicResponse
import com.tes.vodleapp.model.vodle.ConversionResponse
import com.tes.vodleapp.model.vodle.TTSConversionRequest
import com.tes.vodleapp.model.vodle.VodlesAroundRequest
import com.tes.vodleapp.model.vodle.VodlesAroundResponse
import com.tes.vodleapp.model.vodle.VoiceInfoResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class VodleDataSourceImpl @Inject constructor(
    private val vodleService: VodleService
) : VodleDataSource {
    override suspend fun fetchVodlesAround(
        centerLocation: Location,
        northEastLocation: Location,
        southWestLocation: Location
    ): Result<VodlesAroundResponse> = runCatching {
        vodleService.fetchVodlesAround(
            VodlesAroundRequest(
                centerLatitude = centerLocation.lat,
                centerLongitude = centerLocation.lng,
                northEastLatitude = northEastLocation.lat,
                northEastLongitude = northEastLocation.lng,
                southWestLatitude = southWestLocation.lat,
                southWestLongitude = southWestLocation.lng
            )
        )
    }

    override suspend fun uploadVodle(
        recordingFile: File,
        writer: String,
        recordType: String,
        streamingUrl: String,
        location: Location
    ): Result<BasicResponse> = runCatching {
        val requestBody: RequestBody = recordingFile.asRequestBody("audio/m4a".toMediaTypeOrNull())
        val multipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("soundFile", recordingFile.name, requestBody)
        vodleService.uploadVodle(
            multipartBody,
            VodleMetaData(
                writer,
                recordType,
                streamingUrl,
                location.lat,
                location.lng
            )
        )
    }

    override suspend fun convertVoice(
        recordingFile: File,
        selectedVoice: String,
        gender: Gender
    ): Result<ConversionResponse> =
        runCatching {
            val requestBody: RequestBody =
                recordingFile.asRequestBody("audio/m4a".toMediaTypeOrNull())
            val multipartBody: MultipartBody.Part =
                MultipartBody.Part.createFormData(
                    "sound_file",
                    recordingFile.name,
                    requestBody
                )
            vodleService.convertVoice(multipartBody, selectedVoice, gender.value)
        }

    override suspend fun convertTTS(
        content: String,
        selectedVoice: String
    ): Result<ConversionResponse> =
        runCatching {
            vodleService.convertTTS(TTSConversionRequest(content, selectedVoice))
        }

    override suspend fun fetchVoiceInfo(): Result<VoiceInfoResponse> = runCatching {
        vodleService.fetchVoiceTypes()
    }
}
