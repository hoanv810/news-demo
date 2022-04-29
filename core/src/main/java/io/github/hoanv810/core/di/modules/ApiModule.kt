package io.github.hoanv810.core.di.modules

import android.content.Context
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.hoanv810.core.BuildConfig
import io.github.hoanv810.core.network.services.ApiService
import io.github.hoanv810.core.utils.CoreUtils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Singleton

/**
 * @author hoanv
 * @since 10/6/20
 */
@InstallIn(ApplicationComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(@ApplicationContext context: Context): Cache {
        val size: Long = 100 * 1024 * 1024
        return Cache(context.cacheDir, size)
    }

    @Provides
    @Singleton
    fun provideLogging(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context,
        logger: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(offlineInterceptor(context))
            .addNetworkInterceptor(onlineInterceptor())
            .addNetworkInterceptor(logger)
            .connectTimeout(SERVICE_CONNECT_TIMEOUT, MILLISECONDS)
            .readTimeout(SERVICE_READ_TIMEOUT, MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    /**
     * Remove header Pragma and Cache-control because they prevent us from caching.
     * If there is Internet, get the cache that was stored 60 seconds ago
     * Otherwise, get the cache was stored within 30 days
     */
    private fun onlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("X-Api-Key", BuildConfig.API_KEY)
                .method(original.method, original.body)

            val response = chain.proceed(request.build())
            val maxAge = 10

            return@Interceptor response.newBuilder()
                .header(HEADER_CACHE_CONTROL, "public, max-age=$maxAge")
                .removeHeader(HEADER_PRAGMA)
                .build()
        }
    }

    private fun offlineInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            var request: Request = chain.request()
            if (!CoreUtils.hasConnection(context)) {
                val maxStale = 60 * 60 * 24 * 30
                request = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("X-Api-Key", BuildConfig.API_KEY)
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader(HEADER_PRAGMA)
                    .build()
            }
            return@Interceptor chain.proceed(request)
        }
    }
}

const val HEADER_PRAGMA = "Pragma"
const val HEADER_CACHE_CONTROL = "Cache-Control"
const val SERVICE_CONNECT_TIMEOUT: Long = 30000
const val SERVICE_READ_TIMEOUT: Long = 30000