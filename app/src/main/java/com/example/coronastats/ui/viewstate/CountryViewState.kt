package com.example.coronastats.ui.viewstate

import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import retrofit2.Response

class CountryViewState(data: Response<CovidStatsInfo>?, error: String, state: State) :
    BaseViewState<Response<CovidStatsInfo>>() {

    init {
        this.data = data
        this.currentState = state
        this.error = error
    }

    companion object {
        val SUCCESS = CountryViewState(null, "", State.SUCCESS)
        val FAILED = CountryViewState(null, "", State.FAILED)
        val PROGRESS = CountryViewState(null, "", State.LOADING)

    }

}