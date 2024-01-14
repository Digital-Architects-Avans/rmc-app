package com.digitalarchitects.rmc_app.domain.repo

import java.io.File

interface FileRepository {
    suspend fun uploadImage(file: File): String
}