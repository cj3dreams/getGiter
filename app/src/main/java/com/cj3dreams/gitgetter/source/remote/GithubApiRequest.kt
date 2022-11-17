package com.cj3dreams.gitgetter.source.remote

import com.cj3dreams.gitgetter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.gitgetter.utils.AppConstants.TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApiRequest {

    @Headers("Accept: application/vnd.github+json",
    "Authorization: Bearer $TOKEN")
    @GET("users/{username}/repos")
    suspend fun getRepoByUsername(@Path("username") username: String): Response<List<GitRepoItemModel>>

}
