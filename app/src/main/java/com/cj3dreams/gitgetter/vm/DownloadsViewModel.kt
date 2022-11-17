package com.cj3dreams.gitgetter.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.gitgetter.model.entities.DownloadsEntity
import com.cj3dreams.gitgetter.repo.DataRepositoryImpl
import com.cj3dreams.gitgetter.usecase.GetAllDownloadsFromLocalUserCase
import com.cj3dreams.gitgetter.usecase.InsertDownloadToLocalUseCase
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
            }
        }
    fun insertDownloadToLocal (dE: DownloadsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            insertDownloadToLocalUseCase.invoke(dE)
            getAllDownloadsFromLocal()
        }
    fun doAllOnesToMinuses() = viewModelScope.launch(Dispatchers.IO) {
        getAllDownloadsFromLocalUserCase.invoke().collect{ list->
            list.forEach {
                if (it.isDownloaded == 0)
                    insertDownloadToLocalUseCase.invoke(it.copy(isDownloaded = -1))
            }
        }
        getAllDownloadsFromLocal()
    }
}