package com.example.instagarmclone.presentation.signUp

import androidx.compose.runtime.mutableStateOf
import com.example.instagarmclone.InstagramViewModel
import com.example.instagarmclone.R
import com.example.instagarmclone.common.composables.isValidEmail
import com.example.instagarmclone.common.composables.isValidPassword
import com.example.instagarmclone.common.composables.passwordMatches
import com.example.instagarmclone.common.snackbar.SnackbarManager
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.domain.services.StorageService
import com.example.instagarmclone.util.Constants.FEEDS_SCREEN
import com.example.instagarmclone.util.Constants.PROFILE_SCREEN
import com.example.instagarmclone.util.Constants.SIGN_UP_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,

) :
    InstagramViewModel() {
    var signUpUiState = mutableStateOf(SignUpUiState())
        private set
    private val email
        get() = signUpUiState.value.email
    private val password
        get() = signUpUiState.value.password
    private val repeatPassword
        get() = signUpUiState.value.repeatPassword
    private val userName
        get() = signUpUiState.value.userName


    fun onEmailChange(value: String) {
        signUpUiState.value = signUpUiState.value.copy(email = value)
    }

    fun onUserNameChange(value: String) {
        signUpUiState.value = signUpUiState.value.copy(userName = value)
    }

    fun onPasswordChange(value: String) {
        signUpUiState.value = signUpUiState.value.copy(password = value)
    }

    fun onRepeatPasswordChange(value: String) {
        signUpUiState.value = signUpUiState.value.copy(repeatPassword = value)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.password_error)
            return
        }
        if (!repeatPassword.passwordMatches(password)) {
            SnackbarManager.showMessage(R.string.repeat_password_error)
            return
        }
        launchCatching {
            accountService.linkAccount(email, password, userName)
            openAndPopUp(PROFILE_SCREEN, SIGN_UP_SCREEN)
        }


    }
}

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val userName: String = "",
)