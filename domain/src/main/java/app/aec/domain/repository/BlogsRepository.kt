package app.aec.domain.repository

import app.aec.domain.model.Blog

interface BlogsRepository {
    suspend fun getBlogs(): List<Blog>
}