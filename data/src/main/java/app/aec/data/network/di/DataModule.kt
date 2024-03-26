package app.aec.data.network.di

import app.aec.common.Constant
import app.aec.data.network.ApiService
import app.aec.data.repository.BlogsRepositoryImpl
import app.aec.domain.repository.BlogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient()
        httpClient.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header("app-id", Constant.APP_ID)
            chain.proceed(requestBuilder.build())
        })
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(httpClient)
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