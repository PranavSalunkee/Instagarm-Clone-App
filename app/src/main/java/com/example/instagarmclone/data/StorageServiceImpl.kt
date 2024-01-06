package com.example.instagarmclone.data

import com.example.instagarmclone.domain.model.User
import com.example.instagarmclone.domain.services.AccountService
import com.example.instagarmclone.domain.services.StorageService
import com.example.instagarmclone.util.Constants.COLLECTION_NAME_USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore, private val auth: AccountService,
    private val firebaseAuth: FirebaseAuth,

    ) : StorageService {


    override val user: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    launch { // Launch a coroutine here
                        this@callbackFlow.trySend(auth.currentUser?.let { getUser() } ?: User())
                    }
                }
            firebaseAuth.addAuthStateListener(listener)
            awaitClose { firebaseAuth.removeAuthStateListener(listener) }
        }

    override suspend fun getUser(): User? {
        return firestore.collection(COLLECTION_NAME_USERS).document(auth.currentUserId).get()
            .await().toObject()
    }


    override suspend fun save(user: User): Unit {

        firestore.collection(COLLECTION_NAME_USERS).document(user.userId).set(user).await()
    }
}