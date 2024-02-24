package com.example.composepractice.di

import android.app.Application
import androidx.room.Room
import com.example.composepractice.data.ConverterDatabase
import com.example.composepractice.data.ConverterRepository
import com.example.composepractice.data.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerConverterDatabase(app: Application): ConverterDatabase {
        return Room.databaseBuilder(
            app,
            ConverterDatabase::class.java,
            "converter_data_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providerConverterRepository(db: ConverterDatabase): ConverterRepository {
        return ConverterRepositoryImpl(db.converterDAO)
    }
}