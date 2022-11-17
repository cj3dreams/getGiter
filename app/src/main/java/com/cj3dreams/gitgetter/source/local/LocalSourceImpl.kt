package com.cj3dreams.gitgetter.source.local

import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalSourceImpl(private val dao: DownloadsDao): LocalSource {
    override suspend fun getAllDownloadsFromLocal(): Flow<List<DownloadsEntity>> = withContext(Dispatchers.IO){
        dao.getAllDownloads()
    }
    override suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity) = withContext(Dispatchers.IO){
        dao.insertDownloadToLocal(downloadsEntity)
    }
}