package com.pguarin.sample_kmp_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform