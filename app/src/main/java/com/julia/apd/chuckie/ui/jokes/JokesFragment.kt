package com.julia.apd.chuckie.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.julia.apd.chuckie.R

class JokesFragment : Fragment() {

    private lateinit var viewModel: JokesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(JokesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_jokes_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_joke_list_text)
        viewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}