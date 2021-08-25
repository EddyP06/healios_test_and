package com.eddy.healiostest.ui.features.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.databinding.CommentItemBinding
import com.eddy.healiostest.ui.features.details.adapter.viewholder.CommentViewHolder

class CommentAdapter: RecyclerView.Adapter<CommentViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CommentEntity>() {
        override fun areItemsTheSame(oldItem: CommentEntity, newItem: CommentEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentEntity, newItem: CommentEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    var data: List<CommentEntity>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = CommentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int = data.size
}