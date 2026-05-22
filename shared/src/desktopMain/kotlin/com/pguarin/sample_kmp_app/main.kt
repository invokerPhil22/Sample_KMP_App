package com.pguarin.sample_kmp_app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pguarin.sample_kmp_app.domain.model.PostsModel
import com.pguarin.sample_kmp_app.presentation.ui.PostsScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "sample_kmp_app",
    ) {
        PostsScreen(
            isLoading = false,
            posts = samplePosts,
            errMsg = null,
            onRefreshClick = {},
        )
    }
}

private val samplePosts = listOf(
    PostsModel(
        id = "1",
        title = "Hot reload preview",
        body = "This screen is shared between Android and Desktop.",
        authorLabel = "Desktop",
    ),
    PostsModel(
        id = "2",
        title = "Edit common UI",
        body = "Change PostsScreen in commonMain and run :shared:hotRunDesktop.",
        authorLabel = "Compose",
    ),
)
