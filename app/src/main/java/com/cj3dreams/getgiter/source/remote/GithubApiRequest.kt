package com.cj3dreams.getgiter.source.remote

import com.cj3dreams.getgiter.model.gitrepo.GitRepoResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiRequest {

    @GET("/users/{username}/repos")
    suspend fun getRepoByUsername(@Path("username") username: String): Response<List<GitRepoResponseItem>>

}
