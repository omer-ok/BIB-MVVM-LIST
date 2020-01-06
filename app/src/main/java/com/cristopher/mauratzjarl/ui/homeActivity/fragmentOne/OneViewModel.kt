package com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cristopher.mauratzjarl.Utilz.Coroutines
import com.cristopher.mauratzjarl.data.db.entities.listResponceDB
import com.cristopher.mauratzjarl.data.repositories.ListRepository
import com.cristopher.mauratzjarl.ui.homeActivity.fragmentOne.model.Movie
import kotlinx.coroutines.Job

class OneViewModel(
    private val repository: ListRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private val _listData = MutableLiveData<List<Movie>>()
    val listData : LiveData<List<Movie>>
        get() = _listData
    private lateinit var job: Job

    suspend fun getListData(){

        job = Coroutines.ioThenMain(
            { repository.ListData() },
            {  _listData.value = it }
        )
    }
}
