package com.cj3dreams.gitgetter.repo

import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import com.cj3dreams.gitgetter.source.local.LocalSource
import com.cj3dreams.gitgetter.source.remote.RemoteSource
import kotlinx.coroutines.flow.Flow

class DataRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource): DataRepository {

    override suspend fun getRepoByUsername(username: String) = remoteSource.getRepoByUserName(username)
    override suspend fun getAllDownloadsFromLocal(): Flow<List<DownloadsEntity>> = localSource.getAllDownloadsFromLocal()
    override suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity) =
        localSource.insertDownloadToLocal(downloadsEntity)
}