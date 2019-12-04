package com.julia.apd.chuckie.ui.jokes

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.julia.apd.chuckie.R
import com.julia.apd.chuckie.models.JokeModel


class JokeAdapter(private val context: Context) :
    PagedListAdapter<JokeModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<JokeModel>() {
            override fun areItemsTheSame(oldItem: JokeModel, newItem: JokeModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: JokeModel,
                newItem: JokeModel
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder(LayoutInflater.from(context).inflate(R.layout.joke_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val paintingModel = getItem(position)
        if (paintingModel != null) {
            (holder as JokeViewHolder).bind(paintingModel)
        }

        if (position %2 == 1) {
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        }
        else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }
}

class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(jokeModel: JokeModel) {
        val titleView = itemView.findViewById<TextView>(R.id.joke_text)
        titleView.text = jokeModel.joke
    }
}

