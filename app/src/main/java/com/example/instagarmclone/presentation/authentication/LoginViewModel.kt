package com.example.instagarmclone.presentation.authentication

import androidx.compose.runtime.mutableStateOf
import com.example.instagarmclone.InstagramViewModel
import com.example.instagarmclone.R
import com.example.instagarmclone.common.composables.isValidEmail
import com.example.instagarmclone.common.composables.isValidPassword
import com.example.instagarmclone.common.snackbar.SnackbarManager
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.util.Constants.FEEDS_SCREEN
import com.example.instagarmclone.util.Constants.LOGIN_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val accountService: AccountService) : InstagramViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(value: String) {
        uiState.value = uiState.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        uiState.value = uiState.value.copy(password = value)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.password_error)
            return
        }
        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(FEEDS_SCREEN, LOGIN_SCREEN)

        }


    }
}

data class LoginUiState(
    var email: String = "",
    val password: String = "",
)