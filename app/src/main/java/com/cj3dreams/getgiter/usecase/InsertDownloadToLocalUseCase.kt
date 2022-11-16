package com.cj3dreams.getgiter.usecase

import com.cj3dreams.getgiter.model.entities.DownloadsEntity
import com.cj3dreams.getgiter.repo.DataRepository

class InsertDownloadToLocalUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke(dE: DownloadsEntity) = dataRepository.insertDownloadToLocal(dE)
}