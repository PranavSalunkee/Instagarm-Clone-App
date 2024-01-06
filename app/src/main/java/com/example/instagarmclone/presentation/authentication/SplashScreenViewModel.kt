package com.example.instagarmclone.presentation.authentication

import androidx.compose.runtime.mutableStateOf
import com.example.instagarmclone.InstagramViewModel
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.util.Constants.FEEDS_SCREEN
import com.example.instagarmclone.util.Constants.PROFILE_SCREEN
import com.example.instagarmclone.util.Constants.SIGN_UP_SCREEN
import com.example.instagarmclone.util.Constants.SPLASH_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel@Inject constructor(
    private val accountService: AccountService
) :InstagramViewModel(){
    val showError = mutableStateOf(false)
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        showError.value = false
        if (accountService.hasUser) openAndPopUp(PROFILE_SCREEN,  SPLASH_SCREEN)
        else openAndPopUp(SIGN_UP_SCREEN,  SPLASH_SCREEN)

    }
}