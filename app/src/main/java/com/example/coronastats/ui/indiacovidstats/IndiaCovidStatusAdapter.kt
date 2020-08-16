package com.example.coronastats.ui.indiacovidstats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coronastats.data.latestcasesindia.CovidStatsInfo
import com.example.coronastats.data.latestcasesindia.RegionalItem
import com.example.coronastats.data.latestcasesindia.Summary
import com.example.coronastats.data.latestcasesindia.UnofficialSummaryItem
import com.example.coronastats.databinding.*

class IndiaCovidStatusAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var regionList: List<RegionalItem> = ArrayList()
    private var covidStatsInfo: CovidStatsInfo? = null

    companion object {
        const val VIEW_HEADER = 5
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
            VIEW_HEADER -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemHeaderIndiaStatsBinding.inflate(inflater)
                return ItemHeaderViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemSummaryUnofficialBinding.inflate(inflater)
                return ItemSummaryUnViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return regionList.size + 4
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RegionalViewHolder -> {
                holder.bind(regionList.get(position - 4))
            }
            is ItemLastUpdatedViewHolder -> {
                holder.bind(covidStatsInfo?.lastOriginUpdate)
            }
            is ItemSummaryViewHolder -> {
                holder.bind(covidStatsInfo?.data?.summary)
            }
            is ItemHeaderViewHolder -> {
                holder.bind("Covid-19 status India")
            }
            is ItemSummaryUnViewHolder -> {
                holder.bind(covidStatsInfo?.data?.unofficialSummary?.get(0))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return 5
            1 -> return 2
            2 -> return 3
            3 -> return 4
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
            binding.item = regionalItem
            binding.executePendingBindings()
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

    inner class ItemHeaderViewHolder(private val binding: ItemHeaderIndiaStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String?) {
            with(binding) {
                tvHeader.text = item
            }
        }
    }

    inner class ItemSummaryViewHolder(private val binding: ItemSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Summary?) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    inner class ItemSummaryUnViewHolder(private val binding: ItemSummaryUnofficialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnofficialSummaryItem?) {
            binding.item = item
            binding.executePendingBindings()

        }
    }

}