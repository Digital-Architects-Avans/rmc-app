package com.digitalarchitects.rmc_app.presentation.screens.myaccount

import com.digitalarchitects.rmc_app.domain.model.User

data class MyAccountUIState(
    var users: List<User> = emptyList(),
    var currentUser: User? = null,
    var profileImgSrc: String? = null,
)