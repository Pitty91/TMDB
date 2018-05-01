package com.tmdb.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmdb.R
import com.tmdb.databinding.CustomProductionBinding
import com.tmdb.handler.DetailsHandler
import com.tmdb.model.ProductionCompany
import com.tmdb.rest.URLS

/**
 * Created by PRATIK BHATT on 1/5/18.
 */
class ProductionAdapter(private var handler: DetailsHandler, private var listData: ArrayList<ProductionCompany>) : RecyclerView.Adapter<ProductionAdapter.ProductionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductionViewHolder {
        val customBinding: CustomProductionBinding = DataBindingUtil.inflate(LayoutInflater.from(handler.getContext()), R.layout.custom_production, parent, false)
        return ProductionViewHolder(customBinding.root)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductionViewHolder?, position: Int) {
        holder?.customBinding?.model = listData[position]
        holder?.customBinding?.imageUrl = URLS.IMAGE_URL + listData[position].logo_path
        holder?.customBinding?.executePendingBindings()
    }


    class ProductionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var customBinding: CustomProductionBinding = DataBindingUtil.bind(itemView!!)!!
    }
}