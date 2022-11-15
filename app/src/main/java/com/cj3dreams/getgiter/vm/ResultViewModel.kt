package com.cj3dreams.getgiter.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.getgiter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.getgiter.repo.DataRepositoryImpl
import com.cj3dreams.getgiter.source.remote.RequestResult
import com.cj3dreams.getgiter.usecase.GetRepoByUsernameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultViewModel(private val dataRepository: DataRepositoryImpl) : ViewModel() {

    val repoByUserNameLiveData: MutableLiveData<List<GitRepoItemModel>> = MutableLiveData()

    private val getRepoByUsernameUseCase get() = GetRepoByUsernameUseCase(dataRepository)

    fun getRepoByUsername(username: String) = viewModelScope.launch(Dispatchers.IO) {
        when (val dataResult = getRepoByUsernameUseCase.invoke(username)) {
            is RequestResult.Success ->
                repoByUserNameLiveData.postValue(dataResult.data ?: emptyList())
            is RequestResult.Error -> {
            }
        }
    }
}