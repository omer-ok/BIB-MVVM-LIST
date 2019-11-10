package com.cristopher.mauratzjarl.ui.homeActivity.fragmentFour

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cristopher.mauratzjarl.R

class fourFragment : Fragment() {

    companion object {
        fun newInstance() = fourFragment()
    }

    private lateinit var viewModel: FourViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.four_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FourViewModel::class.java)

    }

}
