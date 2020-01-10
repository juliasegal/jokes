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

        private fun mockJokeServiceModule(): Module = module {
            single { ResponseHandler() }
            single<ChuckNorrisRepository> { ChuckNorrisImpl(FakeJokeApi(), get()) }
        }

        private fun viewModelModules(): Module = module {
            viewModel { JokeViewModel(chuckNorrisRepository = get()) }
            viewModel { JokesViewModel(chuckNorrisRepository = get()) }
            viewModel { NameJokeViewModel(chuckNorrisRepository = get()) }
        }

        fun getModuleList(): List<Module> {
            return listOf(
                mockJokeServiceModule(),
                viewModelModules()
            )
        }
    }
}