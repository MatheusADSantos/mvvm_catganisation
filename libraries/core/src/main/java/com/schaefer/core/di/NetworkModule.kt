package com.schaefer.core.di

import android.content.Context
import com.schaefer.core.BuildConfig
import com.schaefer.core.extension.hasNetwork
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val SEVEN_DAYS_MAX_STALE = 60 * 60 * 24 * 7
private const val SIXTY_SECONDS_MAX_AGE = 60

val networkModule = module {

    single<OkHttpClient> { provideOkHttpClient(androidContext()) }

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<Retrofit> {
        provideRetrofit(
            okHttpClient = get(),
            converterFactory = get()
        )
    }
}

private fun provideOkHttpClient(context: Context) = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (context.hasNetwork() == true)
                request.newBuilder().header(
                    "Cache-Control",
                    "public, max-age=$SIXTY_SECONDS_MAX_AGE"
                ).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=$SEVEN_DAYS_MAX_STALE"
                ).build()
            chain.proceed(request)
        }
        .build()
} else {
    OkHttpClient
        .Builder()
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}
