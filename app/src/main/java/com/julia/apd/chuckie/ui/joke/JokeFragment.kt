package com.julia.apd.chuckie.ui.joke

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.julia.apd.chuckie.R
import com.julia.apd.chuckie.dao.Status
import com.julia.apd.chuckie.ui.namejoke.NameJokeActivity
import kotlinx.android.synthetic.main.fragment_joke.*
import org.koin.android.viewmodel.ext.android.viewModel

class JokeFragment : Fragment() {
    private val jokeViewModel: JokeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        random_joke.setOnClickListener {
            jokeViewModel.getJoke()
        }
        named_joke.setOnClickListener {
            context?.let { startActivity(NameJokeActivity.newInstance(context!!)) }
        }
    }

    private fun initViewModel() {
        jokeViewModel.joke.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> showJoke(it.data!!.joke)
                Status.ERROR -> showError(it.message!!)
                Status.LOADING -> showLoading(true)
            }
        })
    }

    private fun showLoading(show: Boolean = true) {
        joke_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        showLoading(false)
        Snackbar.make(joke_layout, error, Snackbar.LENGTH_LONG).show()
    }

    private fun showJoke(joke: String) {
        showLoading(false)
        JokeDialog.showJoke(context!!, joke)
    }
}