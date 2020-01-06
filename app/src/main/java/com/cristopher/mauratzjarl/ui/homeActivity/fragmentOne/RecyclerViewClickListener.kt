package com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne

import android.view.View
import com.cristopher.mauratzjarl.data.db.entities.listResponceDB
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.model.Movie

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View,movie: Movie)
}