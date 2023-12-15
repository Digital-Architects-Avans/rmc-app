package com.digitalarchitects.rmc_app.data.myaccount

import com.digitalarchitects.rmc_app.R
import com.digitalarchitects.rmc_app.room.UserTable

data class MyAccountUIState(
    val users: List<UserTable> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val imageResourceId: Int = R.drawable.usericon
    )