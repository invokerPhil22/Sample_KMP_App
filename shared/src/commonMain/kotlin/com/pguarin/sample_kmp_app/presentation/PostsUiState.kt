package com.pguarin.sample_kmp_app.presentation

import com.pguarin.sample_kmp_app.domain.model.PostsModel

data class PostsUiState(
    val isLoading: Boolean = false,
    val posts: List<PostsModel> = emptyList(),
    val errMsg: String? = null
)
