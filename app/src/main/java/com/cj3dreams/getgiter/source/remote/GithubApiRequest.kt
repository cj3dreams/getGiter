package com.cj3dreams.getgiter.source.remote

import com.cj3dreams.getgiter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.getgiter.utils.AppConstants.TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApiRequest {

    @Headers("Accept: application/vnd.github+json",
    "Authorization: Bearer $TOKEN")
    @GET("/users/{username}/repos")
    suspend fun getRepoByUsername(@Path("username") username: String): Response<List<GitRepoItemModel>>

}
