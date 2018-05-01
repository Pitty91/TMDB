package com.tmdb.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.tmdb.R
import com.tmdb.databinding.ActivityDetailsBinding
import com.tmdb.handler.DetailsHandler
import com.tmdb.model.Movie
import com.tmdb.model.MovieDetails
import com.tmdb.presenter.DetailsPresenter
import com.tmdb.rest.URLS
import com.tmdb.utils.BaseActivity

/**
 *Created by PRATIK BHATT on 01/05/18.
 */
class DetailsActivity : BaseActivity(), DetailsHandler {

    private lateinit var detailsBinding: ActivityDetailsBinding
    private lateinit var detailsPresenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding = DataBindingUtil.setContentView(this@DetailsActivity, R.layout.activity_details)
        detailsBinding.movie = intent.getSerializableExtra("movie") as Movie
        detailsBinding.handler = this
        detailsBinding.imageUrl = URLS.IMAGE_URL + detailsBinding.movie!!.backdrop_path
        detailsBinding.title = detailsBinding.movie!!.title

        detailsPresenter = DetailsPresenter(this)
        Handler().postDelayed({
            detailsPresenter.getMovieDetails(detailsBinding.movie?.id.toString())
        },1500)
    }

    override fun setResponse(response: MovieDetails) {
        detailsBinding.movieDetails = response
        detailsBinding.executePendingBindings()

        detailsBinding.tvBudgetTitle.visibility = View.VISIBLE
        detailsBinding.tvBudget.visibility = View.VISIBLE
        detailsBinding.deviderThree.visibility = View.VISIBLE

        when {
            response.production_companies.size > 0 -> {
                detailsBinding.rvProduction.visibility = View.VISIBLE
                detailsBinding.tvProductionTitle.visibility = View.VISIBLE
                var layoutManager = LinearLayoutManager(this)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                detailsBinding.rvProduction.layoutManager = layoutManager
                detailsBinding.rvProduction.hasFixedSize()
                var listAdapter = com.tmdb.view.ProductionAdapter(this, response.production_companies)
                detailsBinding.rvProduction.adapter = listAdapter
            }
            else -> {
                detailsBinding.rvProduction.visibility = View.GONE
                detailsBinding.tvProductionTitle.visibility = View.GONE
            }
        }
    }

    override fun onClickLink() {
        if (!TextUtils.isEmpty(detailsBinding.movieDetails?.homepage)) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(detailsBinding.movieDetails?.homepage)
            startActivity(i)
        }
    }

    override fun getContext(): Context {
        return this@DetailsActivity
    }

    override fun getParentView(): View {
        return detailsBinding.root
    }

}
