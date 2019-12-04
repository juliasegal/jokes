package com.julia.apd.chuckie.module

import com.julia.apd.chuckie.ui.joke.JokeViewModel
import com.julia.apd.chuckie.ui.jokes.JokesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { JokeViewModel(chuckNorrisRepository = get()) }
    viewModel { JokesViewModel(chuckNorrisRepository = get()) }
}
