package app.aec.domain.use_cases

import app.aec.common.Resource
import app.aec.domain.model.Blog
import app.aec.domain.repository.BlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(private val blogsRepository: BlogsRepository) {
    operator fun invoke(): Flow<Resource<List<Blog>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val res = blogsRepository.getBlogs()
                emit(Resource.Success(data = res))
            } catch (e: Exception) {
                emit(Resource.Error("Error occurred"))
            }
        }
    }
}