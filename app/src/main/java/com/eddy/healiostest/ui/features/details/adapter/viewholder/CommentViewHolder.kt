package com.eddy.healiostest.ui.features.details.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.databinding.CommentItemBinding
import kotlinx.android.extensions.LayoutContainer

class CommentViewHolder(private val itemViewBinding: CommentItemBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root), LayoutContainer {
    override val containerView: View?
        get() = itemViewBinding.root

    fun bindView(commentEntity: CommentEntity) {
        with(itemViewBinding) {
            nameTextView.text = commentEntity.name
            bodyTextView.text = commentEntity.body
            emailTextView.text = commentEntity.email
        }
    }
}