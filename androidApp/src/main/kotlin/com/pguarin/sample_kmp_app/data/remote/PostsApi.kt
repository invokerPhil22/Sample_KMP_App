package com.pguarin.sample_kmp_app.data.remote

import retrofit2.http.GET
interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}