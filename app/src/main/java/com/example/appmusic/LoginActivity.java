package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editTextLoginUsername, editTextLoginPassword;
    Button btnLogin;
    TextView textViewCreatAcc;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Mapping();

        textViewCreatAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextLoginUsername.getText().toString();
                password = editTextLoginPassword.getText().toString();

                if(username.length() > 0 && password.length() > 0)
                {
                    DataClient dataClient = APIUtils.getData(); // thực hiện việc gửi phương thức và khi có dữ liệu sẽ trả về trong đây
                    Call<List<User>> callback = dataClient.LoginData(username,password);
                    callback.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            ArrayList<User> arrUser = (ArrayList<User>) response.body();
                            if(arrUser.size() > 0)
                            {
                                Log.d("login",arrUser.get(0).getUser());
                                Log.d("login",arrUser.get(0).getPass());
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                                Log.d("a",t.getMessage().toString());

                                Toast.makeText(LoginActivity.this, "Không tìm thấy tài khoản này", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    private void Mapping()
    {
        editTextLoginUsername = (EditText) findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textViewCreatAcc = (TextView) findViewById(R.id.textViewCreatAcc);
    }
}