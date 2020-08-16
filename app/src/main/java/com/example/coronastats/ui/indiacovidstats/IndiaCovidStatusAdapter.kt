package com.example.coronastats.ui.indiacovidstats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.data.latestcasesindia.RegionalItem
import com.example.coronastats.data.latestcasesindia.Summary
import com.example.coronastats.data.latestcasesindia.UnofficialSummaryItem
import com.example.coronastats.databinding.ItemLastUpdatedBinding
import com.example.coronastats.databinding.ItemRegionalBinding
import com.example.coronastats.databinding.ItemSummaryBinding
import com.example.coronastats.databinding.ItemSummaryUnofficialBinding

class IndiaCovidStatusAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var regionList: List<RegionalItem> = ArrayList()
    private var covidStatsInfo: CovidStatsInfo? = null

    companion object {
        const val VIEW_TYPE_REGIONAL = 1
        const val VIEW_TYPE_LAST_UPDATED = 2
        const val VIEW_TYPE_SUMMARY = 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_REGIONAL -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemRegionalBinding.inflate(inflater)
                return RegionalViewHolder(binding)
            }
            VIEW_TYPE_LAST_UPDATED -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemLastUpdatedBinding.inflate(inflater)
                return ItemLastUpdatedViewHolder(binding)
            }
            VIEW_TYPE_SUMMARY -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemSummaryBinding.inflate(inflater)
                return ItemSummaryViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemSummaryUnofficialBinding.inflate(inflater)
                return ItemSummaryUnViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return regionList.size + 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegionalViewHolder -> {
                holder.bind(regionList.get(position - 3))
            }
            is ItemLastUpdatedViewHolder -> {
                holder.bind(covidStatsInfo?.lastOriginUpdate)
            }
            is ItemSummaryViewHolder -> {
                holder.bind(covidStatsInfo?.data?.summary)
            }
            is ItemSummaryUnViewHolder -> {
                holder.bind(covidStatsInfo?.data?.unofficialSummary?.get(0))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return 2
            1 -> return 3
            2 -> return 4
            else -> return 1
        }
    }

    fun setData(covidStatusInfo: CovidStatsInfo?) {
        covidStatsInfo = covidStatusInfo
        if (covidStatusInfo?.data?.regional != null) {
            regionList = covidStatusInfo.data.regional as List<RegionalItem>
        }
    }

    inner class RegionalViewHolder(private val binding: ItemRegionalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(regionalItem: RegionalItem) {
            with(binding) {
                regionName.text = regionalItem.loc
                txtConfirmed.text = "Confirmed : " + regionalItem.totalConfirmed
                txtRecovered.text = "Recovered : " + regionalItem.discharged
                txtDeaths.text = "Deaths : " + regionalItem.deaths
            }
        }

    }

    inner class ItemLastUpdatedViewHolder(private val binding: ItemLastUpdatedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String?) {
            with(binding) {
                tvLastUpdated.text = "Last updated : " + item
            }
        }
    }

    inner class ItemSummaryViewHolder(private val binding: ItemSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Summary?) {
            with(binding) {
                txtConfirmed.text =
                    "Confirmed : " + item?.confirmedCasesForeign + item?.confirmedCasesIndian
                txtRecovered.text = "Recovered : " + item?.discharged
                txtDeaths.text = "Deaths : " + item?.deaths
            }
        }
    }

    inner class ItemSummaryUnViewHolder(private val binding: ItemSummaryUnofficialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnofficialSummaryItem?) {
            with(binding) {
                txtConfirmed.text = "Confirmed : " + item?.active
                txtRecovered.text = "Recovered : " + item?.recovered
                txtDeaths.text = "Deaths : " + item?.deaths
            }
        }
    }

}