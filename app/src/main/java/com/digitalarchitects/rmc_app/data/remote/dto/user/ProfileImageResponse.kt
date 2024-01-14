package com.digitalarchitects.rmc_app.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class ProfileImageResponse (
    val profileImageSrc: String
)