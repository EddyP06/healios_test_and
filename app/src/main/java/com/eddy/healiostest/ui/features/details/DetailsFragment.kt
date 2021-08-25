package com.eddy.healiostest.ui.features.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.eddy.healiostest.R
import com.eddy.healiostest.databinding.FragmentDetailsBinding
import com.eddy.healiostest.ui.common.BaseFragment
import com.eddy.healiostest.ui.extension.showSnackbar
import com.eddy.healiostest.ui.extension.viewBinding
import com.eddy.healiostest.ui.features.details.adapter.CommentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: BaseFragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding()
    private val viewModel: DetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()
    private val adapter = CommentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getComments(args.post.id)
        viewModel.getUser(args.post.userId)
    }

    override fun initViews() {
        with(binding) {
            titleTextView.text = args.post.title
            bodyTextView.text = args.post.body
            swipeRefresh.setOnRefreshListener {
                viewModel.getComments(args.post.id)
                viewModel.getUser(args.post.userId)
            }
            commentRecyclerView.adapter = adapter
        }
    }

    override fun bindData() {
        viewModel.commentLiveData.observe(viewLifecycleOwner) {
            adapter.data = it
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                nameTextView.text = it.name
                emailTextView.text = it.email
                usernameTextView.text = it.username
            }
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showSnackbar(it)
            }
        }
    }
}