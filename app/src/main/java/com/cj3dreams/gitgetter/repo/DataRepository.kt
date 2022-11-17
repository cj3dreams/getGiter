package com.cj3dreams.gitgetter.repo

import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import com.cj3dreams.gitgetter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.gitgetter.source.remote.RequestResult
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun getRepoByUsername(username: String): RequestResult<List<GitRepoItemModel>>
    suspend fun getAllDownloadsFromLocal(): Flow<List<DownloadsEntity>>
    suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity)

}