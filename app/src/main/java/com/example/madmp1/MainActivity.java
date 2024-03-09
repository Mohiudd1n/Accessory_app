package com.example.madmp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madmp1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static String userEmail;
    TextView t1 ;
    ActivityMainBinding binding;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        String email,pass;
        setContentView(binding.getRoot());
        t1= (TextView) findViewById(R.id.signup);
        dbManager = new DBManager(this);

        Boolean checkinglogin = dbManager.checklogin();
        if (checkinglogin == false){

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.loginEmail.getText().toString().trim();
                String pass = binding.loginPass.getText().toString().trim();

                if(email.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter all Fields!",Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checklogin = dbManager.checkemailpassword(email,pass);

                    if(checklogin==true){
                        dbManager.addstate(email);
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                        userEmail = email;
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Login Failed, Please Check Credentials!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }); } else{
            String email1 = binding.loginEmail.getText().toString().trim();
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            userEmail = email1;
            finish();}

        binding.signup.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,signup.class));
            }
        });
    }

}
