package com.cj3dreams.gitgetter.usecase

import com.cj3dreams.gitgetter.repo.DataRepository

class GetRepoByUsernameUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke(username: String) = dataRepository.getRepoByUsername(username)
}