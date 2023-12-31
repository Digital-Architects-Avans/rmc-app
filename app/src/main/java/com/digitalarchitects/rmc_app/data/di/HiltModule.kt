package com.digitalarchitects.rmc_app.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.digitalarchitects.rmc_app.data.repo.RentalRepositoryImpl
import com.digitalarchitects.rmc_app.data.repo.UserRepositoryImpl
import com.digitalarchitects.rmc_app.data.repo.VehicleRepositoryImpl
import com.digitalarchitects.rmc_app.domain.repo.RentalRepository
import com.digitalarchitects.rmc_app.domain.repo.UserRepository
import com.digitalarchitects.rmc_app.domain.repo.VehicleRepository
import com.digitalarchitects.rmc_app.remote.RmcApiService
import com.digitalarchitects.rmc_app.room.RmcRoomDatabase
import com.digitalarchitects.rmc_app.room.RmcRoomDatabaseRepo
import com.digitalarchitects.rmc_app.room.RmcRoomDatabaseRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): RmcApiService {
        return retrofit.create(RmcApiService::class.java)
    }

    // Provide a singleton instance of Json
    @Provides
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Singleton
    @Provides
    fun providesRetrofit(json: Json): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://10.0.2.2:8080/")
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
        prefs: SharedPreferences,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepositoryImpl(db, api, prefs, dispatcher)
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
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

}