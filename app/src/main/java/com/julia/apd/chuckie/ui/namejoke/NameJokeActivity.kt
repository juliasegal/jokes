package com.julia.apd.chuckie.ui.namejoke

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.julia.apd.chuckie.R
import com.julia.apd.chuckie.dao.Status
import com.julia.apd.chuckie.ui.joke.JokeDialog
import com.julia.apd.chuckie.ui.joke.JokeDialogCompleteCallback
import kotlinx.android.synthetic.main.activity_name_joke.*
import org.koin.android.viewmodel.ext.android.viewModel


class NameJokeActivity : AppCompatActivity(), JokeDialogCompleteCallback {
    private val nameJokeViewModel: NameJokeViewModel by viewModel()

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, NameJokeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_joke)
        setTitle(R.string.name_title)
        initViewModel()
        name_done_button.setOnClickListener {
            val namesEntry = name_entry.text
            val names = namesEntry.trim().split(" ")
            if (names.size == 2) {
                nameJokeViewModel.getJoke(names[0], names[1])
            } else {
                hideKeyboard()
                showError(getString(R.string.name_error_first_last))
            }
        }
    }

    private fun initViewModel() {
        nameJokeViewModel.joke.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> showJoke(it.data!!.joke)
                Status.ERROR -> showError(it.message!!)
                Status.LOADING -> showLoading()
            }
        })
    }

    private fun showLoading(show: Boolean = true) {
        joke_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        showLoading(false)
        Snackbar.make(name_joke_layout, error, Snackbar.LENGTH_LONG).show()
    }

    private fun showJoke(joke: String) {
        showLoading(false)
        hideKeyboard()
        JokeDialog.showJoke(this, joke, this)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun complete() {
        //finish()
    }
}
