package com.pguarin.sample_kmp_app.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pguarin.sample_kmp_app.data.remote.RetrofitClient
import com.pguarin.sample_kmp_app.data.repo.PostsRepoImpl
import com.pguarin.sample_kmp_app.domain.repo.PostsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repo: PostsRepo = PostsRepoImpl(RetrofitClient.postApi)
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostsUiState(isLoading = true))
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }


    fun loadPosts() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errMsg = null
            )

            try {
                val posts = repo.getPosts()
                _uiState.value = PostsUiState(posts = posts)
            } catch (err: Exception) {
                _uiState.value = PostsUiState(
                    errMsg = err.message ?: "Something went wrong",
                )
            }
        }

    }

}