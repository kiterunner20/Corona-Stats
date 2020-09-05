package com.example.coronastats.ui.alllist

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coronastats.Repository
import com.example.coronastats.data.CountryWiseStatsItem
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CountryViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private var countryData: MutableLiveData<Response<List<CountryWiseStatsItem>>> =
        MutableLiveData()

    fun getCountryWiseList(): MutableLiveData<Response<List<CountryWiseStatsItem>>> {
        return countryData
    }

    fun makeCountryAPICall() {


        viewModelScope.launch {
            try {
                updateUiThread(repository.getCountryList())
                Log.v("1", "Background thread " + Thread.currentThread().name)
            } catch (e: Exception) {
                Log.v("Error", "Exception handled")
            }
        }


    }

    suspend fun updateUiThread(countryDataResponse: Response<List<CountryWiseStatsItem>>) {
        withContext(Main) {
            countryData.postValue(countryDataResponse)
            Log.v("2", "UI thread : " + Thread.currentThread().name)
        }
    }


}