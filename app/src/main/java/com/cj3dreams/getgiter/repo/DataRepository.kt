package com.cj3dreams.getgiter.repo

import com.cj3dreams.getgiter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.getgiter.source.remote.RequestResult

interface DataRepository {
    suspend fun getRepoByUsername(username: String): RequestResult<List<GitRepoItemModel>>

}