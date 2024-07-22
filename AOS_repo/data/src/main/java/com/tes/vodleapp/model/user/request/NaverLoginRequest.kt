package com.tes.vodleapp.model.user.request

data class NaverLoginRequest(
    val userCode: String,
    val provider: String,
    val signature: String
)
