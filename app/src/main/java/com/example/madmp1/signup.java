package com.example.madmp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.madmp1.databinding.ActivitySignupBinding;

public class signup extends AppCompatActivity {
    ImageView btn;
    Button signup;
    ActivitySignupBinding binding;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn = (ImageView) findViewById(R.id.btn1);
//        signup = (Button) findViewById(R.id.submitbtn);
        dbManager = new DBManager(this);

        binding.submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.signupName.getText().toString().trim();
                String email = binding.signupEmail.getText().toString().trim();
                String pass = binding.signupPass.getText().toString().trim();
                String confirm = binding.signupConfirm.getText().toString().trim();

                if(email.equals("") || name.equals("") || pass.equals("") || confirm.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(confirm)){
                        Boolean checkUserEmail = dbManager.checkemail(email);

                        if(checkUserEmail == false){
                            Boolean insert = dbManager.inputdetails(name,email,pass,"inactive");

                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Signup was Successful!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signup.this,MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Signup has Failed!",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"User already Exists, Please Login",Toast.LENGTH_SHORT).show();
                        }

                    } else{
                        Toast.makeText(getApplicationContext(),"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(signup.this,HomeActivity.class));
//                finish();
//            }
//        });

    }
    }
