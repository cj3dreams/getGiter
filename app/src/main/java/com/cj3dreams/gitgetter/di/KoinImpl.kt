package com.cj3dreams.gitgetter.di

import android.app.Application
import androidx.room.Room
import com.cj3dreams.gitgetter.repo.DataRepositoryImpl
import com.cj3dreams.gitgetter.source.local.AppDb
import com.cj3dreams.gitgetter.source.local.DownloadsDao
import com.cj3dreams.gitgetter.source.local.LocalSourceImpl
import com.cj3dreams.gitgetter.source.remote.GithubApiRequest
import com.cj3dreams.gitgetter.source.remote.RemoteSourceImpl
import com.cj3dreams.gitgetter.utils.AppConstants.BASE_URL
import com.cj3dreams.gitgetter.vm.DownloadsViewModel
import com.cj3dreams.gitgetter.vm.ResultViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    fun <Api> provideRetrofit(api: Class<Api>) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also { client->
                val logging =HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(logging)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)

    factory { provideRetrofit(GithubApiRequest::class.java) }

}
val dataSourceModule = module {

    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, AppDb::class.java, "AppDb")
            .fallbackToDestructiveMigration()
            .build()

    fun provideDao(appDb: AppDb) = appDb.downloadsDao()

    fun provideDataRepository(api: GithubApiRequest, dao: DownloadsDao) =
        DataRepositoryImpl(RemoteSourceImpl(api), LocalSourceImpl(dao))

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single {provideDataRepository(get(), get())}

    viewModel {
        ResultViewModel(get())
    }
}
val downloadsViewModel = module {
    viewModel {
        DownloadsViewModel(get())
    }
}