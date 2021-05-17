package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextSignUpFullName, editTextSignUpEmail, editTextSignUpUsername, editTextSignUpPassword;
    Button buttonSignUp;
    TextView textViewShortcut;
    String fullname , username , password , email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Mapping();
        textViewShortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fullname = editTextSignUpFullName.getText().toString();
                username = editTextSignUpUsername.getText().toString();
                password = editTextSignUpPassword.getText().toString();
                email = editTextSignUpEmail.getText().toString();

                if(fullname.length() > 0 && username.length() > 0 && password.length() > 0 && email.length() > 0  )
                {
                    DataClient insertData = APIUtils.getData(); // gửi những phương thức từ trong interface lên cho server
                                                                // và sau khi có dữ liệu sẽ trả về cho phương thức getData, tạo ra 1 đối tượng object là DataClient và nhận những dữ liệu
                                                                // bên trong class của DataClient
                    Call<String> callback = insertData.InsertData(fullname,username,password,email); // gọi đến func insertData để đẩy dữ liệu lên
                    callback.enqueue(new Callback<String>() { // thực hiện upload lên
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) { // thành công
                            String result = response.body(); // lấy dữ liệu ra
                            if(result.equals("success")) // success được echo bên php
                            {
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) { // thất bại
                            Log.d("aaa",t.getMessage());
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }




            }
        });


    }

    private  void Mapping()
    {
        editTextSignUpFullName = (EditText) findViewById(R.id.editTextSignUpFullName);
        editTextSignUpEmail = (EditText) findViewById(R.id.editTextSignUpEmail);
        editTextSignUpUsername = (EditText) findViewById(R.id.editTextSignUpUsername);
        editTextSignUpPassword = (EditText) findViewById(R.id.editTextSignUpPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textViewShortcut = (TextView) findViewById(R.id.textViewShortcut);
    }
}