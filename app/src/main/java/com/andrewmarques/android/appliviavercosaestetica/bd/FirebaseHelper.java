package com.andrewmarques.android.appliviavercosaestetica.bd;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * Firebase Helper:
 * salva e resgata informaçoes na nuvem
 */

public class FirebaseHelper {

    private static FirebaseAuth mAuth;

    public static FirebaseAuth getInstance ( ) {
        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    public static FirebaseUser getCurrentUser () {
        FirebaseUser currentUser = getInstance().getCurrentUser();
        return currentUser;
    }

    public static Task<AuthResult> cadastrarUsuario (String email, String senha){
        return getInstance().createUserWithEmailAndPassword(email, senha);
    }

    public static Task<AuthResult> loginUsuario (String email, String senha){
        return getInstance().signInWithEmailAndPassword(email, senha);
    }

    public static void signOut(Context context) {
        getInstance().signOut();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, gso);
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            // Ação a ser realizada após o logout
        });
    }

}
