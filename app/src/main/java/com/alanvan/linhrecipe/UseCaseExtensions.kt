package com.alanvan.linhrecipe

import androidx.lifecycle.MutableLiveData
import com.alanvan.domain.base.BaseUseCase

fun <T, Params> BaseUseCase<T, Params>.loadWithLiveData(
        viewStateLiveData: MutableLiveData<ViewState<T>>,
        dataLiveData: MutableLiveData<T>): BaseUseCase<T, Params> {

    addToDoBeforeTasks {
        viewStateLiveData.value = ViewState.Loading(dataLiveData.value == null)
    }

    addToOnResultTasks {
        dataLiveData.value = it
    }

    addToOnErrorTasks {
        viewStateLiveData.value = ViewState.Error(it)
    }

    addToDoAfterTasks {
        viewStateLiveData.value = ViewState.Success()
    }

    return this
}