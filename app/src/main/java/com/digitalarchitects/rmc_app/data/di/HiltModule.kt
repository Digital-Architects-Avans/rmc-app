package com.digitalarchitects.rmc_app.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.digitalarchitects.rmc_app.data.auth.AuthInterceptor
import com.digitalarchitects.rmc_app.data.local.RmcRoomDatabase
import com.digitalarchitects.rmc_app.data.local.RmcRoomDatabaseRepo
import com.digitalarchitects.rmc_app.data.local.RmcRoomDatabaseRepoImpl
import com.digitalarchitects.rmc_app.data.remote.RmcApiService
import com.digitalarchitects.rmc_app.data.repo.FileRepositoryImpl
import com.digitalarchitects.rmc_app.data.repo.RentalRepositoryImpl
import com.digitalarchitects.rmc_app.data.repo.UserRepositoryImpl
import com.digitalarchitects.rmc_app.data.repo.VehicleRepositoryImpl
import com.digitalarchitects.rmc_app.domain.repo.FileRepository
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserPreferencesRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.domain.util.LocalDateAdapter
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.datetime.LocalDate
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun providePlacesClient(application: Application): PlacesClient {
        return Places.createClient(application.applicationContext)
    }

    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): RmcApiService {
        return retrofit.create(RmcApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(LocalDate::class.java, LocalDateAdapter())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(
        rmcApiService: Provider<RmcApiService>,
        userPreferencesRepository: UserPreferencesRepository
    ): AuthInterceptor {
        return AuthInterceptor(rmcApiService, userPreferencesRepository)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {

        // Change to production URL when deploying
        // Also change the URL in FileRepositoryImpl.kt for getting profile images
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesRoomDb(
        @ApplicationContext appContext: Context
    ): RmcRoomDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            RmcRoomDatabase::class.java,
            "room_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesRoomDatabaseRepo(
        database: RmcRoomDatabase
    ): RmcRoomDatabaseRepo {
        return RmcRoomDatabaseRepoImpl(
            database.userDao,
            database.vehicleDao,
            database.rentalDao
        )
    }

    @Provides
    @Singleton
    fun providesUserRepo(
        db: RmcRoomDatabaseRepo,
        api: RmcApiService,
        userPreferencesRepository: UserPreferencesRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepositoryImpl(db, api, userPreferencesRepository, dispatcher)
    }

    @Provides
    @Singleton
    fun providesVehicleRepo(
        db: RmcRoomDatabaseRepo,
        api: RmcApiService,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): VehicleRepository {
        return VehicleRepositoryImpl(db, api, dispatcher)
    }

    @Provides
    @Singleton
    fun providesRentalRepo(
        db: RmcRoomDatabaseRepo,
        api: RmcApiService,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): RentalRepository {
        return RentalRepositoryImpl(db, api, dispatcher)
    }

    @Provides
    @Singleton
    fun providesFileRepo(
        api: RmcApiService
    ): FileRepository {
        return FileRepositoryImpl(api)
    }

}