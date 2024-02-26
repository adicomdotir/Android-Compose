package app.aec.domain.di

import app.aec.domain.repository.BlogsRepository
import app.aec.domain.use_cases.GetBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideGetBlogsUseCase(blogsRepository: BlogsRepository): GetBlogsUseCase {
        return GetBlogsUseCase(blogsRepository)
    }
}