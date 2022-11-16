package com.cj3dreams.getgiter.source.local

import com.cj3dreams.getgiter.model.entities.DownloadsEntity
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    suspend fun getAllDownloadsFromLocal(): Flow<List<DownloadsEntity>>
    suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity)
}