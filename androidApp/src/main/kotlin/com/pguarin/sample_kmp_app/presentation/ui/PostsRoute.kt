package com.pguarin.sample_kmp_app.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pguarin.sample_kmp_app.presentation.PostsViewModel

@Composable
fun PostsRoute(
    viewModel: PostsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PostsScreen(
        isLoading = uiState.isLoading,
        posts = uiState.posts,
        errMsg = uiState.errMsg,
        onRefreshClick = viewModel::loadPosts,
    )
}
