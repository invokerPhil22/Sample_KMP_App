package com.pguarin.sample_kmp_app.data.repo

import com.pguarin.sample_kmp_app.data.remote.PostsApi
import com.pguarin.sample_kmp_app.data.remote.toDomain
import com.pguarin.sample_kmp_app.domain.model.PostsModel
import com.pguarin.sample_kmp_app.domain.repo.PostsRepo

class PostsRepoImpl(
    private val postsApi: PostsApi,
) : PostsRepo {
    override suspend fun getPosts(): List<PostsModel> {
        return postsApi.getPosts().map { postDto ->
            postDto.toDomain()
        }
    }
}
