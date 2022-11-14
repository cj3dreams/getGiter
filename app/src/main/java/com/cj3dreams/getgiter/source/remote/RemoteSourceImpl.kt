package com.cj3dreams.getgiter.source.remote

import com.cj3dreams.getgiter.model.gitrepo.GitRepoResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSourceImpl(private val api: GithubApiRequest): RemoteSource {
    override suspend fun getRepoByUserName(username: String): RequestResult<List<GitRepoResponseItem>> =
        withContext(Dispatchers.IO){
            try {
                val response = api.getRepoByUsername(username)
                if (response.isSuccessful) return@withContext RequestResult.Success(response.body()!!)
                else return@withContext RequestResult.Error(Exception(response.message()))
            }catch (e: Exception){
                return@withContext RequestResult.Error(e)
            }
    }

}