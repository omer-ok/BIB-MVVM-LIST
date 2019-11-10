package com.cristopher.mauratzjarl.ui.homeActivity.fragmentTwo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cristopher.mauratzjarl.R

class twoFragment : Fragment() {

    companion object {
        fun newInstance() = twoFragment()
    }

    private lateinit var viewModel: TwoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.two_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TwoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
