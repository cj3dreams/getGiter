package com.cj3dreams.getgiter.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cj3dreams.getgiter.R
import com.cj3dreams.getgiter.model.gitrepo.GitRepoItemModel

class ResultAdapter
    (private val list: List<GitRepoItemModel>,
     private val setOnClickListener: View.OnClickListener):
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    class ResultViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemRepoCardView = view.findViewById(R.id.itemRepoCardView) as CardView
        val itemRepoLogoImgView = view.findViewById(R.id.itemRepoLogoImgView) as ImageView
        val itemRepoNameTx = view.findViewById(R.id.itemRepoNameTx) as TextView
        val itemRepoDescTx = view.findViewById(R.id.itemRepoDescTx) as TextView
        val itemRepoCreatedAtTx = view.findViewById(R.id.itemRepoCreatedAtTx) as TextView
        val itemRepoDownloadTx = view.findViewById(R.id.itemRepoDownloadTx) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)

        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemRepoNameTx.text = itemData.name
        holder.itemRepoDescTx.text = itemData.description
        holder.itemRepoCreatedAtTx.text = itemData.created_at

        holder.itemRepoDownloadTx.setOnClickListener(setOnClickListener)
        holder.itemRepoDownloadTx.tag = "https://api.github.com/repos/${itemData.full_name}/zipball/master"

        holder.itemRepoCardView.setOnClickListener(setOnClickListener)
        holder.itemRepoCardView.tag = itemData.html_url

        glide(holder.itemView.context, holder.itemRepoLogoImgView, itemData.owner.avatar_url)

    }

    override fun getItemCount() = list.size

    private fun glide(context: Context?, imageView: ImageView, urlToImage: String?) {
        context?.let {
            Glide.with(it).load(urlToImage).diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView)
        }
    }
}