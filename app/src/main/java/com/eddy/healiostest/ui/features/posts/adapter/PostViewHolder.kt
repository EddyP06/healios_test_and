package com.eddy.healiostest.ui.features.posts.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.databinding.PostsItemBinding
import kotlinx.android.extensions.LayoutContainer

class PostViewHolder(private val itemViewBinding: PostsItemBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root), LayoutContainer {
    override val containerView: View?
        get() = itemView.rootView

    var onItemClickListener: (() -> Unit)? = null

    fun bindView(post: PostEntity) {
        with(itemViewBinding) {
            titleTextView.text = post.title
            bodyTextView.text = post.body
            root.setOnClickListener {
                onItemClickListener?.invoke()
            }
        }
    }
}