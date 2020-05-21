package com.rojer_ko.notes.presentation.ui.splash

import com.rojer_ko.notes.data.errors.NoAuthException
import com.rojer_ko.notes.data.repository.Repository
import com.rojer_ko.notes.presentation.ui.base.BaseViewModel


class SplashViewModel(private val repository: Repository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(isAuth = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}
