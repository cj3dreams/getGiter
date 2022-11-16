package com.cj3dreams.getgiter.view.ui

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cj3dreams.getgiter.R
import kotlinx.android.synthetic.main.fragment_downloads.*
import java.io.BufferedReader
import java.io.File

class DownloadsFragment : Fragment() {
    private var downloadId:Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_downloads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        downloadBtn.setOnClickListener {
            val url = "https://www.learningcontainer.com/download/sample-zip-files/?wpdmdl=1637&refresh=6364db68931311667554152"
            val fileName = "ccoo"

            downloadZip(url, fileName)
        }
    }
    private fun downloadZip(url: String, fileName: String){
        try {
            val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val zipLink = Uri.parse(url)
            val request = DownloadManager.Request(zipLink)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                    or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("application/zip")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("Загружается $fileName")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+fileName+".zip")

            downloadId = downloadManager.enqueue(request)
            val broadcastReceiver = object: BroadcastReceiver(){
                override fun onReceive(p0: Context?, p1: Intent?) {
                    val id:Long? = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if(id==downloadId) Toast.makeText(requireContext(), "Hooray", Toast.LENGTH_SHORT).show()
                }
            }
            requireActivity().registerReceiver(broadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


        }catch (e: Exception){
            Log.e("Error", e.message.toString())
        }
    }
}