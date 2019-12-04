package com.julia.apd.chuckie.mocks

import android.app.Application
import com.julia.apd.chuckie.dao.ChuckNorrisImpl
import com.julia.apd.chuckie.dao.ChuckNorrisRepository
import com.julia.apd.chuckie.networking.ResponseHandler
import com.julia.apd.chuckie.remote.ChuckNorrisApi
import com.julia.apd.chuckie.ui.joke.JokeViewModel
import com.julia.apd.chuckie.ui.jokes.JokesViewModel
import com.julia.apd.chuckie.ui.namejoke.NameJokeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinTestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinTestApp)
            modules(getModuleList())
        }
    }

    companion object {

        private fun jokeServiceModule(api: ChuckNorrisApi = FakeJokeApi()): Module = module {
            factory<ChuckNorrisRepository> { ChuckNorrisImpl(api, ResponseHandler()) }
        }

        private fun viewModelModules(): Module = module {
            viewModel { JokeViewModel(chuckNorrisRepository = get()) }
            viewModel { JokesViewModel(chuckNorrisRepository = get()) }
            viewModel { NameJokeViewModel(chuckNorrisRepository = get()) }
        }

        fun getModuleList(
            jokeApi: ChuckNorrisApi = FakeJokeApi()
        ): List<Module> {
            return listOf(
                jokeServiceModule(jokeApi),
                viewModelModules()
            )
        }

        fun changeDependencies(jokeApi: ChuckNorrisApi  = FakeJokeApi()) {
            stopKoin()
            startKoin {
                modules(getModuleList(jokeApi))
            }
        }
    }
}