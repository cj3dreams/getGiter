package com.cj3dreams.getgiter.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.getgiter.model.entities.DownloadsEntity
import com.cj3dreams.getgiter.repo.DataRepositoryImpl
import com.cj3dreams.getgiter.usecase.GetAllDownloadsFromLocalUserCase
import com.cj3dreams.getgiter.usecase.InsertDownloadToLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DownloadsViewModel(private val dataRepository: DataRepositoryImpl): ViewModel() {

    val downloadsDbLiveData = MutableLiveData<List<DownloadsEntity>>()

    private val getAllDownloadsFromLocalUserCase get() = GetAllDownloadsFromLocalUserCase(dataRepository)
    private val insertDownloadToLocalUseCase get() = InsertDownloadToLocalUseCase(dataRepository)

    fun getAllDownloadsFromLocal() =
        viewModelScope.launch(Dispatchers.IO) {
            getAllDownloadsFromLocalUserCase.invoke().collect { values ->
                downloadsDbLiveData.postValue(values)
//                if(!values.isNullOrEmpty()) isDbEmpty.postValue(false)
//                else isDbEmpty.postValue(true)
            }
        }
    fun insertDownloadToLocal (dE: DownloadsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            insertDownloadToLocalUseCase.invoke(dE)
            getAllDownloadsFromLocal()
        }

}