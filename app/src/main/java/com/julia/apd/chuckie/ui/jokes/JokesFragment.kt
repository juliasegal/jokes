package com.julia.apd.chuckie.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.julia.apd.chuckie.R
import kotlinx.android.synthetic.main.fragment_jokes_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class JokesFragment : Fragment() {

    private val jokesViewModel: JokesViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var jokesLayoutManager: LinearLayoutManager
    private lateinit var jokesAdapter: JokeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jokes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokesAdapter = JokeAdapter(context!!)
        jokesLayoutManager = LinearLayoutManager(activity).apply {
            recycleChildrenOnDetach = true
        }
        recyclerView = jokes_list.apply {
            layoutManager = jokesLayoutManager
            adapter = jokesAdapter
        }
        initViewModel()
    }

    private fun initViewModel() {
        showLoading(true)
        jokesViewModel.pagedJokes.observe(viewLifecycleOwner, Observer {
            showLoading(false)
            jokesAdapter.submitList(it)
        })
        jokesViewModel.getError()?.observe(viewLifecycleOwner, Observer {
            showLoading(false)
            showError(it)
        })
    }

    private fun showLoading(show: Boolean = true) {
        jokes_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        Snackbar.make(jokes_layout, error, Snackbar.LENGTH_LONG).show()
    }
}