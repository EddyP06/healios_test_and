package com.eddy.healiostest.ui.features.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.databinding.PostsItemBinding

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<PostEntity>() {
        override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    var data: List<PostEntity>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differ = AsyncListDiffer(this, diffCallback)

    var onItemClickListener: ((PostEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding).apply {
            onItemClickListener = {
                this@PostsAdapter.onItemClickListener?.invoke(data[bindingAdapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int = data.size
}