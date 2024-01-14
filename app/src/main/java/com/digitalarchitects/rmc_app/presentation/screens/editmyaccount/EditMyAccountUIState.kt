package com.digitalarchitects.rmc_app.presentation.screens.editmyaccount

import android.net.Uri
import com.digitalarchitects.rmc_app.domain.model.UserType

data class EditMyAccountUIState (
    val userId : String = "",
    val email: String = "",
    val password: String = "",
    val userType: UserType = UserType.CLIENT,
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val street: String = "",
    val buildingNumber: String = "",
    val zipCode: String = "",
    val city: String = "",
    val profileImageSrc: String? = null,
    val imageUri: Uri = Uri.EMPTY,
    val imgUpdated: Boolean = false
)