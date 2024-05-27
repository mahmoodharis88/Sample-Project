package com.example.dummyproject.data.di


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.dummyproject.data.remote.network.NetworkApi
import com.example.dummyproject.presentation.util.Constants
import com.example.dummyproject.presentation.util.NoConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        // Create the Collector
        val chuckerCollector = ChuckerCollector(
            context = context,
            // Toggles visibility of the push notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        // Create the Interceptor
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)

            .build()
    }


    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private fun prepareRequestWithHeader(original: Request?): Request? {

        if (original != null) {
            val builder = original.newBuilder()
            builder.header("Accept", "application/json")
            builder.header("Content-Type", "application/json")
            builder.method(original.method, original.body)

            return builder.build()
        }

        return null
    }


    @Singleton
    @Provides
    fun provideHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        logging: HttpLoggingInterceptor,
        noConnectionInterceptor: NoConnectionInterceptor
    ): OkHttpClient {


        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(noConnectionInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = prepareRequestWithHeader(original)
                chain.proceed(request!!)
            }.addInterceptor(chuckerInterceptor)
            .build()


    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }


}