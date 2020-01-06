package com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.cristopher.mauratzjarl.R
import com.cristopher.mauratzjarl.Utilz.Coroutines
import com.cristopher.mauratzjarl.data.db.entities.listResponceDB
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.adapter.MoviesAdapter
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.model.Movie
import kotlinx.android.synthetic.main.one_fragment.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class oneFragment : Fragment() , KodeinAware , RecyclerViewClickListener{
    override val kodein by kodein()

   // private var factory: OneViewModelFactory
    private lateinit var viewModel: OneViewModel
    private val factory : OneViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this,factory).get(OneViewModel::class.java)


        Coroutines.main {
            viewModel.getListData()
        }


        viewModel.listData.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view_list.also {
                it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)

                it.adapter = MoviesAdapter(movies, this)
            }
        })

        viewModel.listData.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view_list2.also {
                it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL ,false)
                it.setHasFixedSize(true)

                it.adapter = MoviesAdapter(movies.reversed(), this)
            }
        })

    }

    override fun onRecyclerViewItemClick(view: View, movie: Movie) {
        when(view.id){
            R.id.button_book -> {
                Toast.makeText(requireContext(), "Book Button Clicked", Toast.LENGTH_LONG).show()
            }
            /*R.id.layout_like ->{
                Toast.makeText(requireContext(), "Like Layout Clicked", Toast.LENGTH_LONG).show()
            }*/
        }
    }



}
