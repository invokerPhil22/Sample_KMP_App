package com.pguarin.sample_kmp_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pguarin.sample_kmp_app.presentation.ui.PostsRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            PostsRoute()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    PostsRoute()
}