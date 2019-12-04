package com.julia.apd.chuckie.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.julia.apd.chuckie.R

class JokeFragment : Fragment() {

    private lateinit var jokeViewModel: JokeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        jokeViewModel = ViewModelProviders.of(this).get(JokeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }
}