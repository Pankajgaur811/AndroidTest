package com.example.testzevo.ui.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testzevo.data.models.Article
import com.example.testzevo.databinding.TopNewsHeadlineItemLayoutBinding

class TopNewsHeadLineAdapter(
    private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<TopNewsHeadLineAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopNewsHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.urlToImage)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopNewsHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }


}