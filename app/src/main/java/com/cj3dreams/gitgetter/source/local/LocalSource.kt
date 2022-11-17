package com.cj3dreams.gitgetter.source.local

import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun getAllDownloadsFromLocal(): Flow<List<DownloadsEntity>>
    suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity)
}