package com.cj3dreams.gitgetter.usecase

import com.cj3dreams.gitgetter.repo.DataRepository

class GetAllDownloadsFromLocalUserCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke() = dataRepository.getAllDownloadsFromLocal()
}