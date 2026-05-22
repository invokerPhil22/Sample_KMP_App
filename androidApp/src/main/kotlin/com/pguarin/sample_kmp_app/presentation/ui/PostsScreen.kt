package com.pguarin.sample_kmp_app.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pguarin.sample_kmp_app.components.ui.AppButton
import com.pguarin.sample_kmp_app.components.ui.ErrorView
import com.pguarin.sample_kmp_app.components.ui.LoadingView
import com.pguarin.sample_kmp_app.components.ui.SectionTitle
import com.pguarin.sample_kmp_app.domain.model.PostsModel
import com.pguarin.sample_kmp_app.presentation.PostsViewModel


@Composable
fun PostsRoute(
    viewModel: PostsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PostsScreen(
        isLoading = uiState.isLoading,
        posts = uiState.posts,
        errMsg = uiState.errMsg,
        onRefreshClick = viewModel::loadPosts
    )


}

@Composable
fun PostsScreen(
    isLoading: Boolean,
    posts: List<PostsModel>,
    errMsg: String?,
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier,


    ) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 20.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    SectionTitle(text = "Posts")
                    Text(
                        text = "JSONPlaceholder API",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                }
                AppButton(
                    text = "Refresh",
                    onClick = onRefreshClick,
                )


            }
            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> LoadingView()
                errMsg != null -> ErrorView(
                    message = errMsg,
                    onRetryClick = onRefreshClick


                )

                else -> PostsList(posts = posts)

            }

        }

    }

}

@Composable
private fun PostsList(
    posts: List<PostsModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = posts,
            key = { post -> post.id },
        ) { post ->
            PostCard(post = post)
        }
    }
}

@Composable
private fun PostCard(
    post: PostsModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = post.authorLabel,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}