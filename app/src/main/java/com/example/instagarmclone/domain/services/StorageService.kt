package com.example.instagarmclone.domain.services

import com.example.instagarmclone.domain.model.User
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val user: Flow<User>
    suspend fun getUser():User?
    suspend fun save(user:User)
}