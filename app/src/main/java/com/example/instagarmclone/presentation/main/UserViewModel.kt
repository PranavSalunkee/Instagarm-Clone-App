package com.example.instagarmclone.presentation.main

import androidx.compose.runtime.mutableStateOf
import com.example.instagarmclone.InstagramViewModel
import com.example.instagarmclone.domain.model.User
import com.example.instagarmclone.domain.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val storageService: StorageService):InstagramViewModel() {
    var fetch = true
    init {
        launchCatching{
            val user = storageService.user
            delay(1000)
            fetch = false
        }
    }
}


