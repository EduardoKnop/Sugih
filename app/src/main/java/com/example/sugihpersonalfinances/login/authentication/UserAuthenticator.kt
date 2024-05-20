package com.example.sugihpersonalfinances.login.authentication

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth

object UserAuthenticator {

    val auth: FirebaseAuth = Firebase.auth

    fun createAccountWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Log.d(
                    "UserAuthenticator",
                    "createAccountWithEmail:success (user: $user)"
                    /*
                    *  I  Creating user with eduk.coelho@hotmail.com with empty reCAPTCHA token
                    *  D  Notifying id token listeners about user ( 4lNuPSxGYtVWPHxyAP85qTPI75w2 ).
                    *  D  Notifying auth state listeners about user ( 4lNuPSxGYtVWPHxyAP85qTPI75w2 ).
                    *  D  createAccountWithEmail:success (user: com.google.firebase.auth.internal.zzac@7bd6a57)
                    * */
                )
            } else {
                Log.d(
                    "UserAuthenticator",
                    "createAccountWithEmail:failure (exception: ${task.exception})"
                    /*
                    *  I  Creating user with eduk.coelho@hotmail.com with empty reCAPTCHA token
                    *  E  Initial task failed for action RecaptchaAction(action=signUpPassword)with exception - The email address is already in use by another account.
                    *  D  createAccountWithEmail:failure
                    * */
                )
                /*when (task.exception) {
                    is FirebaseAuthWeakPasswordException -> { // It should not occur, there's a local function to analyze it
                        // TODO: Send to Crashlytics
                    }

                    is FirebaseAuthInvalidCredentialsException -> { // It should not occur, there's a local function to analyze it
                        // TODO: Send to Crashlytics
                    }

                    is FirebaseAuthUserCollisionException -> { // User should not know this
                        // TODO: Send to Crashlytics? User should not know this issue
                    }
                }*/
            }

        }
    }

}