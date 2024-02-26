package app.aec.data.repository

import app.aec.data.mappers.toDomain
import app.aec.data.network.ApiService
import app.aec.data.network.utils.SafeApiRequest
import app.aec.domain.model.Blog
import app.aec.domain.repository.BlogsRepository
import javax.inject.Inject

class BlogsRepositoryImpl @Inject constructor(private val apiService: ApiService) : BlogsRepository,
    SafeApiRequest() {
    override suspend fun getBlogs(): List<Blog> {
        val response = safeApiRequest {
            apiService.getBlogs()
        }
        return response.data?.toDomain() ?: emptyList()
    }
}