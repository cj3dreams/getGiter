package com.cj3dreams.getgiter.repo

import com.cj3dreams.getgiter.source.remote.RemoteSource

class DataRepositoryImpl(
    private val remoteSource: RemoteSource, ): DataRepository {

    override suspend fun getRepoByUsername(username: String) = remoteSource.getRepoByUserName(username)
}