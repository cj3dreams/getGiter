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
import androidx.recyclerview.widget.LinearLayoutManager
import com.cj3dreams.getgiter.R
import com.cj3dreams.getgiter.model.entities.DownloadsEntity
import com.cj3dreams.getgiter.view.adapter.DownloadsAdapter
import com.cj3dreams.getgiter.vm.DownloadsViewModel
import kotlinx.android.synthetic.main.fragment_downloads.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.BufferedReader
import java.io.File

class DownloadsFragment : Fragment(), View.OnClickListener {
    private val downloadsViewModel: DownloadsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_downloads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getSerializable("downloadsEntity") != null){
            toast((requireArguments().getSerializable("downloadsEntity") as DownloadsEntity).toString())

            val downloadsEntity = requireArguments().getSerializable("downloadsEntity") as DownloadsEntity
            downloadZip(downloadsEntity)
        }else downloadsViewModel.getAllDownloadsFromLocal()

        recyclerViewDownloads.layoutManager = LinearLayoutManager(requireContext())
        downloadsViewModel.downloadsDbLiveData.observe(viewLifecycleOwner, {
            recyclerViewDownloads.adapter = DownloadsAdapter(it, this)
        })
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.itemDownloadCardView ->{}
        }
    }

    private fun downloadZip(dE: DownloadsEntity){
        try {
            var downloadId:Long = 0

            val request = DownloadManager.Request(Uri.parse(dE.url))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                    or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("application/zip")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("Загружается ${dE.name}")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+dE.name+".zip")

            val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadId = downloadManager.enqueue(request)
            toast("Началась загрузка")
            downloadsViewModel.insertDownloadToLocal(dE.copy(id = downloadId))
            val broadcastReceiver = object: BroadcastReceiver(){
                override fun onReceive(p0: Context?, p1: Intent?) {
                    val id:Long? = p1?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if(id==downloadId) {
                        toast("Загрузка завершена")
                        downloadsViewModel.insertDownloadToLocal(dE.copy(id = downloadId, isDownloaded = 1, downloaded_at = "now"))
                    }
                }
            }
            requireActivity().registerReceiver(broadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


        }catch (e: Exception){
            Log.e("Error", e.message.toString())
            toast("Что-то пошло не так")
        }
    }

    private fun toast(text: String) = Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}