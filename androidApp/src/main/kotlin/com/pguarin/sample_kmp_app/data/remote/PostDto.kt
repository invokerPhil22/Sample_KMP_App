package com.pguarin.sample_kmp_app.data.remote

import com.pguarin.sample_kmp_app.domain.model.PostsModel

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)

fun PostDto.toDomain(): PostsModel {
    return PostsModel(
        id = id.toString(),
        title = title,
        body = body,
        authorLabel = "User: $userId",
    )
}

