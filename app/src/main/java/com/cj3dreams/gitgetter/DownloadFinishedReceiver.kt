package com.cj3dreams.gitgetter

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import com.cj3dreams.gitgetter.vm.DownloadsViewModel
import java.text.SimpleDateFormat
import java.util.*

class DownloadFinishedReceiver(private val downloadId: Long, private val dE: DownloadsEntity,
                               private var downloadsViewModel: DownloadsViewModel
                               ): BroadcastReceiver() {

    @SuppressLint("SimpleDateFormat")
    override fun onReceive(p0: Context?, p1: Intent?) {
        val id:Long? = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        if(id==downloadId) {
            val c = Calendar.getInstance()
            downloadsViewModel.insertDownloadToLocal(dE.copy(id = downloadId, isDownloaded = 1,
                downloaded_at =  SimpleDateFormat("dd.MM.yy HH:mm").format(c.time)))
            Toast.makeText(p0, "Загрузка завершена", Toast.LENGTH_SHORT).show()
        }
    }
}