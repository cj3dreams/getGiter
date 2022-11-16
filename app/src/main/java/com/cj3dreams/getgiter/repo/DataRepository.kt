package com.cj3dreams.getgiter.repo

import com.cj3dreams.getgiter.model.entities.DownloadsEntity
import com.cj3dreams.getgiter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.getgiter.source.remote.RequestResult
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun getRepoByUsername(username: String): RequestResult<List<GitRepoItemModel>>
    suspend fun getAllDownloadsFromLocal(): Flow<List<DownloadsEntity>>
    suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity)

}