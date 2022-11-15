package com.cj3dreams.getgiter.source.remote

import com.cj3dreams.getgiter.model.gitrepo.GitRepoItemModel

interface RemoteSource {
    suspend fun getRepoByUserName(username: String): RequestResult<List<GitRepoItemModel>>
}