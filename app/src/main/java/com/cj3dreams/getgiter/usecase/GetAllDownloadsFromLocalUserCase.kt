package com.cj3dreams.getgiter.usecase

import com.cj3dreams.getgiter.repo.DataRepository

class GetAllDownloadsFromLocalUserCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke() = dataRepository.getAllDownloadsFromLocal()
}