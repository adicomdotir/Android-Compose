package app.aec.data.network.di

import app.aec.data.network.ApiService
import app.aec.data.repository.BlogsRepositoryImpl
import app.aec.domain.repository.BlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideBlogsRepository(apiService: ApiService): BlogsRepository {
        return BlogsRepositoryImpl(apiService = apiService)
    }
}