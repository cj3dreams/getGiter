package com.cj3dreams.gitgetter.view.ui

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cj3dreams.gitgetter.DownloadFinishedReceiver
import com.cj3dreams.gitgetter.MainActivity
import com.cj3dreams.gitgetter.R
import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import com.cj3dreams.gitgetter.view.adapter.DownloadsAdapter
import com.cj3dreams.gitgetter.vm.DownloadsViewModel
import kotlinx.android.synthetic.main.fragment_downloads.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class DownloadsFragment : Fragment(), View.OnClickListener {
    private val downloadsViewModel: DownloadsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        isNormalAppBar(false)

        return inflater.inflate(R.layout.fragment_downloads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getSerializable("downloadsEntity") != null){
            val downloadsEntity = requireArguments().getSerializable("downloadsEntity") as DownloadsEntity
            downloadZip(downloadsEntity)
        }else {
            downloadsViewModel.getAllDownloadsFromLocal()
            downloadsViewModel.doAllOnesToMinuses()
        }

        recyclerViewDownloads.layoutManager = LinearLayoutManager(requireContext())
        downloadsViewModel.downloadsDbLiveData.observe(viewLifecycleOwner, {
            recyclerViewDownloads.adapter = DownloadsAdapter(it, this)
            if (it.isNotEmpty()) downloadsTypeRl.visibility = View.GONE
            else downloadsTypeRl.visibility = View.VISIBLE
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.itemDownloadCardView -> startActivity(Intent(DownloadManager.ACTION_VIEW_DOWNLOADS))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isNormalAppBar(true)
    }

    private fun downloadZip(dE: DownloadsEntity){
        try {
            val downloadId: Long
            val request = DownloadManager.Request(Uri.parse(dE.url))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                    or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("application/zip")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(dE.name)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+dE.name+".zip")

            val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadId = downloadManager.enqueue(request)
            toast("Началась загрузка")
            downloadsViewModel.insertDownloadToLocal(dE.copy(id = downloadId))

            val broadcastReceiver = DownloadFinishedReceiver(downloadId,dE,downloadsViewModel)
            (activity as MainActivity?).let {
            it?.registerReceiver(broadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
            }

        }catch (e: Exception){
            Log.e("Error", e.message.toString())
            toast("Что-то пошло не так")
            requireActivity().onBackPressed()
        }
    }

    private fun isNormalAppBar(isNormal: Boolean){
        val actionBar: androidx.appcompat.app.ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.setHomeButtonEnabled(!isNormal)
        actionBar?.setDisplayHomeAsUpEnabled(!isNormal)
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu[0].isVisible = isNormal
                menu[1].isVisible = isNormal
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        })
    }

    private fun toast(text: String) = Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}