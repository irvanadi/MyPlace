package com.xeylyne.myplace.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xeylyne.myplace.R;
import com.xeylyne.myplace.Models.RequestUser;
import com.xeylyne.myplace.Models.ResultMessage;
import com.xeylyne.myplace.Utilities.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edNickname, edContact, edEmail, edPassword, edConfirm, edInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edNickname = findViewById(R.id.edNickname);
        edContact = findViewById(R.id.edContact);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edConfirm = findViewById(R.id.edConfirm);
        edInstagram = findViewById(R.id.edInstagram);

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }

    private void check() {
        Call<ResultMessage> call = RetrofitInstance.getInstance().register(edNickname.getText().toString(), edPassword.getText().toString(),
                edEmail.getText().toString(), edContact.getText().toString(), edInstagram.getText().toString());
        call.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    CheckData();
                } else {
                    Log.d("Err", "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable t) {
                Log.d("Fail", "onFailure: "+t.getMessage());
            }
        });
    }

    private void CheckData() {
        Call<RequestUser> call1 = RetrofitInstance.getInstance().login(edEmail.getText().toString(), edPassword.getText().toString());
        call1.enqueue(new Callback<RequestUser>() {
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
                        editor.putString("instagram", response.body().getResult().get(0).getINSTAGRAM());
                        editor.apply();

                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
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