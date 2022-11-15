package com.cj3dreams.getgiter.usecase

import com.cj3dreams.getgiter.repo.DataRepository

class GetRepoByUsernameUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke(username: String) = dataRepository.getRepoByUsername(username)
}