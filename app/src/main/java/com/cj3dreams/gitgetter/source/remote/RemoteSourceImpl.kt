package com.cj3dreams.gitgetter.source.remote

import com.cj3dreams.gitgetter.model.gitrepo.GitRepoItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSourceImpl(private val api: GithubApiRequest): RemoteSource {
    override suspend fun getRepoByUserName(username: String): RequestResult<List<GitRepoItemModel>> =
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