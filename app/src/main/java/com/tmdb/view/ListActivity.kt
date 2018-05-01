package com.tmdb.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tmdb.R
import com.tmdb.databinding.ActivityListBinding
import com.tmdb.handler.ListHandler
import com.tmdb.model.Movie
import com.tmdb.presenter.ListPresenter
import com.tmdb.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*

/**
 *Created by PRATIK BHATT on 30/4/18.
 */
class ListActivity : BaseActivity(), ListHandler {

    private lateinit var listBinding: ActivityListBinding
    private lateinit var listPresenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBinding = DataBindingUtil.setContentView(this@ListActivity, R.layout.activity_list)
        listPresenter = ListPresenter()
        listPresenter.getMovieList(this)
        listBinding.handler = this
        revelAnimation(listBinding.root)
    }

    override fun onClickItem(movie: Movie, ivBanner: ImageView, tvTitle: TextView, tvRating: TextView) {
        var detailsIntent = Intent(this@ListActivity, DetailsActivity::class.java)
        detailsIntent.putExtra("movie", movie)

        var p1 = android.support.v4.util.Pair.create<View, String>(ivBanner, ivBanner.transitionName)
        var p2 = android.support.v4.util.Pair.create<View, String>(tvTitle, tvTitle.transitionName)
        var p3 = android.support.v4.util.Pair.create<View, String>(rbRating, rbRating.transitionName)
        val pairs = ArrayList<android.support.v4.util.Pair<View, String>>()
        pairs.add(p1)
        pairs.add(p2)
        pairs.add(p3)
        val pairArray: Array<Pair<View, String>> = pairs.toTypedArray()
        var options2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ListActivity, *pairArray)
        var options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ListActivity, ivBanner, ViewCompat.getTransitionName(ivBanner))
        startActivity(detailsIntent, options2.toBundle())
    }

    override fun getContext(): Context {
        return this@ListActivity
    }

    override fun getParentView(): View {
        return listBinding.root
    }

    override fun setResponse(response: ArrayList<Movie>) {
        when {
            response.size > 0 -> {
                listBinding.rvMovieList.visibility = View.VISIBLE
                listBinding.tvNoData.visibility = View.GONE
                listBinding.rvMovieList.layoutManager = LinearLayoutManager(this)
                listBinding.rvMovieList.hasFixedSize()
                var listAdapter = ListAdapter(this, response)
                listBinding.rvMovieList.adapter = listAdapter
            }
            else -> {
                listBinding.rvMovieList.visibility = View.GONE
                listBinding.tvNoData.visibility = View.VISIBLE
            }
        }
    }
}
