package com.tmdb.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmdb.R
import com.tmdb.databinding.CustomListItemBinding
import com.tmdb.handler.ListHandler
import com.tmdb.model.Movie
import com.tmdb.rest.URLS

/**
 * Created by PRATIK BHATT on 30/4/18.
 */
class ListAdapter(private var handler: ListHandler, private var listData: ArrayList<Movie>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        val customBinding: CustomListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(handler.getContext()), R.layout.custom_list_item, parent, false)
        return ListViewHolder(customBinding.root)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListViewHolder?, position: Int) {
        holder?.customBinding?.model = listData[position]
        holder?.customBinding?.imageUrl = URLS.IMAGE_URL + listData[position].backdrop_path
        holder?.customBinding?.cvMain?.setOnClickListener({
            handler.onClickItem(listData[position], holder?.customBinding?.ivBanner, holder?.customBinding?.tvTitle, holder?.customBinding?.rbRating)
        })
        holder?.customBinding?.executePendingBindings()
    }

    class ListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var customBinding: CustomListItemBinding = DataBindingUtil.bind(itemView!!)!!
    }
}