package com.rs.flickd.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rs.flickd.R
import com.rs.flickd.databinding.MovieListFragmentBinding
import com.rs.flickd.viewModels.TMDBViewModelFactory
import com.rs.flickd.viewModels.TmdbNowPlayingMoviesViewModel
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var tmdbViewModelFactory: TMDBViewModelFactory

    private lateinit var viewModel: TmdbNowPlayingMoviesViewModel

    private lateinit var movieListFragmentBinding: MovieListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        movieListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment,
                container, false)
        //movieListFragmentBinding.movieListRecyclerView.adapter = null

        return movieListFragmentBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, tmdbViewModelFactory).get(TmdbNowPlayingMoviesViewModel::class.java)
        viewModel.getNowPlayingMovies().observe(this, Observer {
            if (it == null) {
                movieListFragmentBinding.isLoading = true
            } else {
                movieListFragmentBinding.isLoading = false
                Timber.d(it.moviesList.size.toString() + "sdmnklsdngklfd")
            }
        }

        )


    }


}