package com.cj3dreams.gitgetter.usecase

import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import com.cj3dreams.gitgetter.repo.DataRepository

class InsertDownloadToLocalUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke(dE: DownloadsEntity) = dataRepository.insertDownloadToLocal(dE)
}