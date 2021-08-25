package com.eddy.healiostest.ui.features.posts

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.eddy.healiostest.R
import com.eddy.healiostest.databinding.FragmentPostsBinding
import com.eddy.healiostest.ui.common.BaseFragment
import com.eddy.healiostest.ui.extension.showSnackbar
import com.eddy.healiostest.ui.extension.viewBinding
import com.eddy.healiostest.ui.features.posts.adapter.PostsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : BaseFragment(R.layout.fragment_posts) {

    private val viewBinding: FragmentPostsBinding by viewBinding()
    private val viewModel: PostViewModel by viewModel()
    private val adapter = PostsAdapter().apply {
        onItemClickListener = { item ->
            findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToDetailsFragment(item))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPosts()
    }

    override fun initViews() {
        with(viewBinding) {
            postsRecyclerView.adapter = adapter
            swipeRefresh.setOnRefreshListener {
                viewModel.getPosts()
            }
        }
    }

    override fun bindData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.swipeRefresh.isRefreshing = it
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showSnackbar(it)
            }
        }
        viewModel.postLiveData.observe(viewLifecycleOwner) {
            adapter.data = it
        }
    }
    override fun initListeners() {

    }

}