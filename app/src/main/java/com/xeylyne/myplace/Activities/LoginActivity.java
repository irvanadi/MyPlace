package com.xeylyne.myplace.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xeylyne.myplace.R;
import com.xeylyne.myplace.Models.RequestUser;
import com.xeylyne.myplace.Utilities.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CheckCredential();

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        txtRegister = findViewById(R.id.txtRegister);

        findViewById(R.id.txtRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCredential();
            }
        });
    }

    private void CheckCredential() {
        SharedPreferences credential = getSharedPreferences("login", MODE_PRIVATE);
        String credentialString = credential.getString("id", "Null");
        if (!credentialString.equalsIgnoreCase("Null")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void setCredential() {
        Call<RequestUser> call = RetrofitInstance.getInstance().login(edEmail.getText().toString(), edPassword.getText().toString());
        call.enqueue(new Callback<RequestUser>() {
            @Override
            public void onResponse(Call<RequestUser> call, Response<RequestUser> response) {
                if (response.isSuccessful()){
                    try {
                        SharedPreferences credential = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = credential.edit();
                        editor.putString("id", response.body().getResult().get(0).getIDUSER());
                        editor.putString("name", response.body().getResult().get(0).getNICKNAME());
                        editor.putString("email", response.body().getResult().get(0).getEMAIL());
                        editor.putString("contact", response.body().getResult().get(0).getCONTACT());
                        editor.putString("instagram",response.body().getResult().get(0).getINSTAGRAM());
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Log.d("Error1", "EMAIL/PASS SALAH");
                    }
                } else {
                    Log.d("Error2", "Server MT");
                }
            }

            @Override
            public void onFailure(Call<RequestUser> call, Throwable t) {
                Log.d("Error3", "Connection");
            }
        });
    }
}
