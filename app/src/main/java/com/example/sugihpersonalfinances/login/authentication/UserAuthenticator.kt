package com.example.sugihpersonalfinances.login.authentication

import android.content.Context
import android.credentials.CredentialOption
import android.credentials.GetCredentialRequest
import android.credentials.GetCredentialRequest.Builder
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import com.example.sugihpersonalfinances.login.constants.LogInConstants
import com.facebook.AccessToken
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.Task
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.coroutineScope

object UserAuthenticator {

    private val TAG = UserAuthenticator.javaClass.simpleName

    val auth: FirebaseAuth = Firebase.auth

    fun createAccountWithEmail(email: String, password: String): Task<AuthResult> {
        val pendingTask = auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(TAG, "createAccountWithEmail:success (user: $user)"
                        /*
                        *  I  Creating user with eduk.coelho@hotmail.com with empty reCAPTCHA token
                        *  D  Notifying id token listeners about user ( 4lNuPSxGYtVWPHxyAP85qTPI75w2 ).
                        *  D  Notifying auth state listeners about user ( 4lNuPSxGYtVWPHxyAP85qTPI75w2 ).
                        *  D  createAccountWithEmail:success (user: com.google.firebase.auth.internal.zzac@7bd6a57)
                        * */
                    )
                } else {
                    Log.d(TAG, "createAccountWithEmail:error (exception: ${result.exception})"
                        /*
                        *  I  Creating user with eduk.coelho@hotmail.com with empty reCAPTCHA token
                        *  E  Initial task failed for action RecaptchaAction(action=signUpPassword)with exception - The email address is already in use by another account.
                        *  D  createAccountWithEmail:failure
                        * */
                    )
                    /*when (result.exception) {
                        is FirebaseAuthWeakPasswordException -> { // It should not occur, there's a local function to analyze it
                            // TODO: Send to Crashlytics
                        }

                        is FirebaseAuthInvalidCredentialsException -> { // It should not occur, there's a local function to analyze it
                            // TODO: Send to Crashlytics
                        }

                        is FirebaseAuthUserCollisionException -> { // User should not know this
                            // TODO: Send to Crashlytics or Another Service? User should not know this issue
                        }
                    }*/
                }
            }

        return pendingTask
    }

    fun logInWithEmail(email: String, password: String): Task<AuthResult> {
        val pendingTask = auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(TAG, "signInWithEmail:success (user: $user)")
                } else {
                    Log.d(TAG, "signInWithEmail:error (exception: ${result.exception})"
                    )
                }
            }

        return pendingTask
    }

    fun logInAnonymously(): Task<AuthResult> {
        val pendingTask = auth.signInAnonymously()
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(TAG, "signInAnonymously:success (user: $user)")
                } else {
                    Log.d(TAG, "signInAnonymously:error (exception: ${result.exception}")
                }
            }

        return pendingTask
    }

    /*suspend fun logInWithGoogle(): Task<AuthResult> {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(true) // true to list only accounts the user already signed in, false otherwise (Must call twice in this case)
                .setServerClientId(LogInConstants.WEB_CLIENT_ID)
                .setAutoSelectEnabled(true) // Enables automatic sin in for returning users
                .setNonce(null) // https://developer.android.com/google/play/integrity/classic#nonce
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            coroutineScope {
                try {
                    val result = credentialManager.getCredential(
                        request = request,
                        context = activityContext
                    )
                    handleSignIn(result)
                }
            }
        } else { // TODO: Excludes when minSdkVersion >= 34

        }

        val pendingTask = auth.signInWithCredential()
            .addOnCompleteListener {

        }

        return pendingTask
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        when (val credential = result.credential) {
            is PublicKeyCredential -> {
                responseJson = credential.authenticationResponseJson
            }
            is PasswordCredential -> {
                val username = credential.id
                val password = credential.password
            }
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    Log.e(TAG, "Unexpected Custom Credential Type")
                }
            }
            else -> {
                Log.e(TAG, "Unexpected Credential Type")
            }
        }
    }*/

    /*fun logInWithFacebook(): Task<AuthResult> {
        val pendingTask = auth.signInWithCredential()
            .addOnCompleteListener {

            }

        return pendingTask
    }*/

    fun logOut() {
        auth.signOut()
    }

    fun logInWithFacebook(token: AccessToken): Task<AuthResult> {
        val credential = FacebookAuthProvider.getCredential(token.token)

        val pendingTask = auth.signInWithCredential(credential)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val user = auth.currentUser
                    Log.d(TAG, "logInWithFacebook:success (user: $user)")
                } else {
                    Log.d(TAG, "logInWithFacebook:error (exception: ${result.exception}")
                }
            }

        return pendingTask
    }

}