package com.github.coutinhonobre.dependency


import com.github.coutinhonobre.api.GeneralApi
import com.github.coutinhonobre.presentation.ProductViewModel
import com.github.coutinhonobre.repository.ProductRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        ProductViewModel(get())
    }
}

val repositoryModule = module {
    single {
        ProductRepository(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): GeneralApi {
        return retrofit.create(GeneralApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/sandbox/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}