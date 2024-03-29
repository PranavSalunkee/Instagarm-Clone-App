package com.example.instagarmclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagarmclone.common.snackbar.SnackbarManager
import com.example.instagarmclone.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class InstagramViewModel:ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }


            },
            block = block
        )
}