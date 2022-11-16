package com.cj3dreams.getgiter.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cj3dreams.getgiter.model.entities.DownloadsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadsDao {
    @Query("SELECT * FROM downloads ORDER by downloaded_at DESC")
    fun getAllDownloads(): Flow<List<DownloadsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadToLocal(downloadsEntity: DownloadsEntity)

}