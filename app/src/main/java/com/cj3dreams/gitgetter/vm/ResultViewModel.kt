package com.cj3dreams.gitgetter.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.gitgetter.model.gitrepo.GitRepoItemModel
import com.cj3dreams.gitgetter.repo.DataRepositoryImpl
import com.cj3dreams.gitgetter.source.remote.RequestResult
import com.cj3dreams.gitgetter.usecase.GetRepoByUsernameUseCase
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