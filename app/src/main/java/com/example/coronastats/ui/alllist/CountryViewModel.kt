package com.example.coronastats.ui.alllist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.coronastats.Repository
import com.example.coronastats.data.CountryWiseStatsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CountryViewModel(application: Application) : AndroidViewModel(application) {


    val repository: Repository =
        Repository()
    var countryData: MutableLiveData<Response<List<CountryWiseStatsItem>>> = MutableLiveData()

    fun getCountryWiseList(): MutableLiveData<Response<List<CountryWiseStatsItem>>> {
        return countryData
    }

    fun makeCountryAPICall() {


        CoroutineScope(Dispatchers.Default).launch {
            updateUiThread(repository.getCountryList())
            Log.v("1", "Background thread " + Thread.currentThread().name)
        }


    }

    suspend fun updateUiThread(countryDataResponse: Response<List<CountryWiseStatsItem>>) {
        withContext(Main) {
            countryData.postValue(countryDataResponse)
            Log.v("2", "UI thread : " + Thread.currentThread().name)
        }
    }


}