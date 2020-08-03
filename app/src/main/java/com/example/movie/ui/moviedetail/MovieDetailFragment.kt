package com.example.movie.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.movie.data.entities.Detail
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.utils.Resource
import com.example.movie.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var binding: FragmentMovieBinding by autoCleared()
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.detail.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null)
                        bindDetail(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.detailCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.detailCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindDetail(detail: Detail) {
        binding.movieTitle.text = detail.title
        binding.genre.text = detail.genre
        binding.year.text = detail.year
        binding.rated.text = detail.rated
        binding.country.text = detail.country
        binding.directorTv.text = detail.director
        binding.writerTv.text = detail.writer
        Glide.with(binding.root)
            .load(detail.poster)
            .into(binding.movieIv)
    }
}