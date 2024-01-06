package com.example.instagarmclone.data


import com.example.instagarmclone.domain.model.User
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.util.Constants
import com.example.instagarmclone.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth,private val FirebaseStore: FirebaseFirestore) :
    AccountService {


    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val firebaseAuthState: Flow<Boolean>
    = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend( auth.currentUser != null)
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }


    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }



    override suspend fun linkAccount(email: String, password: String,userName:String) {


            auth.createUserWithEmailAndPassword(email,password).await()
            val userId = auth.currentUser?.uid!!
            val user = User(userName = userName,userId = userId, email = email, password = password)
            FirebaseStore.collection(Constants.COLLECTION_NAME_USERS).document(userId).set(user).await()


    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun signOut(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))

        }
        catch (e:Exception){
            emit(Response.Error(e.localizedMessage ?: "An Unexpected Error"))
        }


    }

    
}
