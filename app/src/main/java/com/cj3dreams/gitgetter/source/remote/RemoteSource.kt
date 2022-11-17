package com.cj3dreams.gitgetter.source.remote

import com.cj3dreams.gitgetter.model.gitrepo.GitRepoItemModel

interface RemoteSource {
    suspend fun getRepoByUserName(username: String): RequestResult<List<GitRepoItemModel>>
}