package com.example.instagarmclone.domain.services



import com.example.instagarmclone.util.Response
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val firebaseAuthState:Flow<Boolean>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)

    suspend fun linkAccount(email: String, password: String,userName:String)
    suspend fun deleteAccount()
    suspend fun signOut():Flow<Response<Boolean>>
}