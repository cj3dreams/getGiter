package com.cj3dreams.gitgetter.view.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.gitgetter.R
import com.cj3dreams.gitgetter.model.entities.DownloadsEntity

class DownloadsAdapter
    (private val list: List<DownloadsEntity>,
     private val setOnClickListener: View.OnClickListener):
    RecyclerView.Adapter<DownloadsAdapter.DownloadsViewHolder>() {

    class DownloadsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemDownloadCardView = view.findViewById(R.id.itemDownloadCardView) as CardView
        val itemDownloadNameTx = view.findViewById(R.id.itemDownloadNameTx) as TextView
        val itemDownloadUsernameTx = view.findViewById(R.id.itemDownloadUsernameTx) as TextView
        val itemDownloadedAtTx = view.findViewById(R.id.itemDownloadedAtTx) as TextView
        val itemIsDownloadedTx = view.findViewById(R.id.itemIsDownloadedTx) as TextView
        val itemDownloadProgressBar = view.findViewById(R.id.itemDownloadProgressBar) as ProgressBar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_download, parent, false)

        return DownloadsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadsViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemDownloadNameTx.text = itemData.name
        holder.itemDownloadUsernameTx.text = itemData.login
        holder.itemDownloadedAtTx.text = itemData.downloaded_at
        if(itemData.isDownloaded == 0) {
            holder.itemIsDownloadedTx.text = "Загружается"
            holder.itemDownloadProgressBar.progress = 45
            holder.itemDownloadProgressBar.isIndeterminate = true
        }
        else if (itemData.isDownloaded == -1){
            holder.itemIsDownloadedTx.text = "Неизвестно"
            holder.itemDownloadProgressBar.progress = 100
            holder.itemDownloadProgressBar.progressTintList = ColorStateList.valueOf(Color.parseColor("#F44336"))
        }
        holder.itemDownloadCardView.setOnClickListener(setOnClickListener)
    }

    override fun getItemCount() = list.size

}