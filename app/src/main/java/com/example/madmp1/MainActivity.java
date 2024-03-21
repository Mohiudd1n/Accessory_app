package com.example.madmp1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madmp1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static String userEmail;
    TextView t1 ;
    RelativeLayout relativeLayout;
    ActivityMainBinding binding;
    DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        String email,pass;
        setContentView(binding.getRoot());
        binding.loginButton.setEnabled(false);
        t1= (TextView) findViewById(R.id.signup);
        dbManager = new DBManager(this);
        relativeLayout = findViewById(R.id.relativelayout);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loginPass.clearFocus();
                binding.loginEmail.clearFocus();
                binding.loginButton.setTextColor(Color.BLACK);
            }
        });

        Boolean checkinglogin = dbManager.checklogin();
        if (checkinglogin == false){
            binding.loginEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // Check if the EditText has lost focus
                    if (!hasFocus) {
                        // Enable the button when EditText loses focus
                        binding.loginButton.setEnabled(true);
                    }else{
                        binding.loginButton.setEnabled(false);
                    }
                }
            });
            binding.loginPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // Check if the EditText has lost focus
                    if (!hasFocus) {
                        // Enable the button when EditText loses focus
                        binding.loginButton.setEnabled(true);
                    }else{
                        binding.loginButton.setEnabled(false);
                    }
                }
            });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.loginEmail.getText().toString().trim();
                String pass = binding.loginPass.getText().toString().trim();

                if(email.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter all Fields!",Toast.LENGTH_SHORT).show();
                } else if (!check_format_email(email)) {
                    Toast.makeText(getApplicationContext(),"Invalid Email Format", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checklogin = dbManager.checkemailpassword(email,pass);

                    if(checklogin==true){
                        dbManager.addstate(email);
//                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT); // Set toast duration

                        // Set the custom layout for the toast
                        toast.setView(layout);

                        // Start the progress bar animation (optional)
                        ProgressBar progressBar = layout.findViewById(R.id.progress_bar);
                        progressBar.setVisibility(View.VISIBLE); // Make the progress bar visible
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate); // (Optional) Add a rotation animation
                        progressBar.startAnimation(animation);

                        toast.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                finish();
                            }
                        }, 2000);

                        userEmail = email;
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
    public boolean check_format_email(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void hideKeyboard() {
        // Get the InputMethodManager
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // Hide the keyboard
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }
}
