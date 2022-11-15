package com.cj3dreams.getgiter.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cj3dreams.getgiter.MainActivity
import com.cj3dreams.getgiter.R
import com.cj3dreams.getgiter.view.adapter.ResultAdapter
import com.cj3dreams.getgiter.vm.ResultViewModel
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : Fragment(), View.OnClickListener {
    private val resultViewModel: ResultViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_result, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).searchLiveData.observe(viewLifecycleOwner, {
            resultViewModel.getRepoByUsername(it)
        })

        recyclerViewResult.layoutManager = LinearLayoutManager(requireContext())
        resultViewModel.repoByUserNameLiveData.observe(viewLifecycleOwner, {
            recyclerViewResult.adapter = ResultAdapter(it, this)
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.itemRepoDownloadTx -> {
                Toast.makeText(requireContext(), v.tag as String , Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.itemRepoCardView -> {
                Toast.makeText(requireContext(), v.tag as String , Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}