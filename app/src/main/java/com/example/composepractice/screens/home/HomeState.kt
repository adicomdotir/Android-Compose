package com.example.composepractice.screens.home

import app.aec.domain.model.Blog

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<Blog>? = null,
    val error: String = ""
)
