package com.digitalarchitects.rmc_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RmcApplication : Application() {
    // GLOBAL VARIABLE: STAFF JWT token for API authentication
    // (expires on Wednesday 27 March 2024 10:38:57)
    object GlobalVariables {
        var token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMC4wLjAuMDo4MDgwL2hlbGxvIiwiaXNzIjoiaHR0cDovLzAuMC4wLjA6ODA4MC8iLCJlbWFpbCI6InN0YWZmQGVtYWlsLmNvbSIsInVzZXJUeXBlIjoiU1RBRkYiLCJ1c2VySWQiOjIsImV4cCI6MTcxMTUzNTkzN30.9pCat2HbE4aKqmyk5l2hXAQOSadKttxD1ymtHmARSy0"
    }
}