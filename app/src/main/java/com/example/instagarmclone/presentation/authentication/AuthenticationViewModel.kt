package com.example.instagarmclone.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.instagarmclone.InstagramViewModel
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.util.Constants.FEEDS_SCREEN
import com.example.instagarmclone.util.Constants.SIGN_UP_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private  val accountService: AccountService


    ):InstagramViewModel() {
   val isAuthenticated = accountService.hasUser
    private val _isReady = MutableStateFlow(true)
    val isReady = _isReady.asStateFlow()
    var hasUser = false

    init {
        launchCatching(){
            delay(3000)
            _isReady.value = false

        }


    }
}