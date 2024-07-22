package com.tes.vodleapp.api

import com.tes.vodleapp.model.user.response.MyVodleResponse
import retrofit2.http.GET

interface UserService {

    @GET("api/auth/me")
    suspend fun fetchMyVodle(): MyVodleResponse
}
