package com.digitalarchitects.rmc_app.data.repo

import com.digitalarchitects.rmc_app.data.remote.RmcApiService
import com.digitalarchitects.rmc_app.domain.repo.FileRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val rmcApiService: RmcApiService
) : FileRepository {
    override suspend fun uploadImage(file: File): String {
        val response = rmcApiService.uploadImage(
            image = MultipartBody.Part.createFormData(
                "image",
                file.name,
                file.asRequestBody()
            )
        )
        return response.profileImageSrc
    }
}
