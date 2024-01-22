package com.digitalarchitects.rmc_app.fake

import com.digitalarchitects.rmc_app.domain.repo.FileRepository
import java.io.File

class FakeFileRepository: FileRepository {
    override suspend fun uploadImage(file: File): String {
        TODO("Not yet implemented")
    }
}