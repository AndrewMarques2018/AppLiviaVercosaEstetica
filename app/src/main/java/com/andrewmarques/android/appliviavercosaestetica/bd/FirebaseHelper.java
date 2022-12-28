package com.andrewmarques.android.appliviavercosaestetica.bd;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseHelper {

    private static FirebaseAuth mAuth;

    public static FirebaseAuth getInstance ( ) {
        if (mAuth == null){
            mAuth = FirebaseAuth.getInstance();
        }
        return mAuth;
    }

    public static Task<AuthResult> cadastrar_new_user (String email, String senha) throws Exception{
        getInstance();
        return mAuth.createUserWithEmailAndPassword(email, senha);
    }

    public static Task<AuthResult> login_user (String email, String senha){
        getInstance();
        return mAuth.signInWithEmailAndPassword(email, senha);
    }

    public static void singnOut () {
        getInstance();
        if (mAuth.getCurrentUser() != null){
            mAuth.signOut();
        }
    }


}
