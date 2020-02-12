package com.xeylyne.myplace.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xeylyne.myplace.Activities.LoginActivity;
import com.xeylyne.myplace.R;
import com.xeylyne.myplace.Activities.WebViewActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    TextView txtName, txtContact, txtEmail, imgPhoto, txtInstagram, txtSpecificInstagram;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        txtName = view.findViewById(R.id.txtSpecificNamaAccount);
        txtContact = view.findViewById(R.id.txtSpecificContact);
        txtEmail = view.findViewById(R.id.txtSpecificEmailAccount);
        txtInstagram = view.findViewById(R.id.txtSpecificInstagram);

        SharedPreferences credential = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        String name = credential.getString("name", "Null");
        String contact = credential.getString("contact", "Null");
        String email = credential.getString("email", "Null");
        String instagram = credential.getString("instagram", "Null");

        txtName.setText(name);
        txtContact.setText(contact);
        txtEmail.setText(email);
        txtInstagram.setText(instagram);

        view.findViewById(R.id.txtLogOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        view.findViewById(R.id.txtInstagram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("instagram", txtInstagram.getText().toString());
                startActivity(intent);
            }
        });

        view.findViewById(R.id.txtSpecificInstagram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("instagram", txtInstagram.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }

    private void Logout() {
        SharedPreferences credential = getActivity().getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = credential.edit().clear();
        editor.apply();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        getActivity().finish();
        startActivity(intent);
    }
}
