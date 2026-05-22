package com.pguarin.sample_kmp_app.domain.repo

import com.pguarin.sample_kmp_app.domain.model.PostsModel

interface PostsRepo {
    suspend fun getPosts(): List<PostsModel>
}