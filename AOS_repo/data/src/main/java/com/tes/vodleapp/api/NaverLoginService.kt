package com.tes.vodleapp.api

import com.tes.vodleapp.model.user.response.NaverUserIdResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface NaverLoginService {
    @GET("nid/me")
    suspend fun getNaverUserId(
        @Header("Authorization") accessToken: String
    ): NaverUserIdResponse
}
