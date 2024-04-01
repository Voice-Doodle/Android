package com.tes.vodle.datasource.vodle

import android.util.Log
import com.tes.domain.model.Gender
import com.tes.vodle.api.VodleMetaData
import com.tes.vodle.api.VodleService
import com.tes.vodle.model.BasicResponse
import com.tes.vodle.model.vodle.ConversionResponse
import com.tes.vodle.model.vodle.VodlesAroundResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class VodleDataSourceImpl @Inject constructor(
    private val vodleService: VodleService
) : VodleDataSource {
    override suspend fun fetchVodlesAround(): Result<VodlesAroundResponse> = runCatching {
        vodleService.fetchVodlesAround()
    }

    override suspend fun uploadVodle(recordingFile: File): Result<BasicResponse> = runCatching {
        val requestBody: RequestBody = recordingFile.asRequestBody("audio/m4a".toMediaTypeOrNull())
        val multipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("soundFile", recordingFile.getName(), requestBody)
        vodleService.uploadVodle(multipartBody, VodleMetaData())
    }

    override suspend fun convertVoice(recordingFile: File, selectedVoice: String, gender: Gender): Result<ConversionResponse> =
        runCatching {
            val requestBody: RequestBody =
                recordingFile.asRequestBody("audio/m4a".toMediaTypeOrNull())
            val multipartBody: MultipartBody.Part =
                MultipartBody.Part.createFormData(
                    "sound_file",
                    recordingFile.name,
                    requestBody
                )
            Log.d("확인", "변환요청 직전")
            vodleService.convertVoice(multipartBody, selectedVoice, gender.value)
        }
}
