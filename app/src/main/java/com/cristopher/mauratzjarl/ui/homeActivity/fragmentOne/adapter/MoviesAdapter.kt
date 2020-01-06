package com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cristopher.mauratzjarl.R
import com.cristopher.mauratzjarl.data.db.entities.listResponceDB
import com.cristopher.mauratzjarl.databinding.ItemViewBinding
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.RecyclerViewClickListener
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.model.Movie


class MoviesAdapter (
    private val movies: List<Movie>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.recyclerviewMovieBinding.list = movies[position]
        holder.recyclerviewMovieBinding.buttonBook.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.buttonBook, movies[position])
        }
       /* holder.recyclerviewMovieBinding.layoutLike.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.layoutLike, movies[position])
        }*/
    }


    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: ItemViewBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)

}