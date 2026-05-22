package com.pguarin.sample_kmp_app.data.repo

import com.pguarin.sample_kmp_app.data.remote.PostsApi
import com.pguarin.sample_kmp_app.data.remote.toDomain
import com.pguarin.sample_kmp_app.domain.model.PostsModel
import com.pguarin.sample_kmp_app.domain.repo.PostsRepo

class PostsRepoImpl(
    private val postsApi: PostsApi,
) : PostsRepo {
    override suspend fun getPosts(): List<PostsModel> {
        val response = postsApi.getPosts()

        val statusCode = response.code()
        val message = response.message()
        val headers = response.headers()

        return if (response.isSuccessful) {
            response.body()?.map { postDto ->
                postDto.toDomain()
            } ?: emptyList()
        } else {
            throw Exception("API failed: $statusCode $message")
        }
    }
}
