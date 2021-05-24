package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;
import com.example.appmusic.Model.User;

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
    CheckBox checkBoxLogin;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Mapping();

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE); // name: tạo 1 cái name và lưu vào bộ nhớ của ứng dụng

        editTextLoginUsername.setText(sharedPreferences.getString("user",""));
        editTextLoginPassword.setText(sharedPreferences.getString("pass",""));
        checkBoxLogin.setChecked(sharedPreferences.getBoolean("checked",false));

        textViewCreatAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
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

                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                if(checkBoxLogin.isChecked())
                                {
                                    SharedPreferences.Editor editor = sharedPreferences.edit(); // muốn editor file nào thì gọi vào
                                    editor.putString("user",username);
                                    editor.putString("pass",password);
                                    editor.putBoolean("checked",true);
                                    editor.commit(); // xác nhận việc lưu các giá trị trên

                                }
                                else
                                {
                                    SharedPreferences.Editor editorr = sharedPreferences.edit();
                                    editorr.remove("user");
                                    editorr.remove("pass");
                                    editorr.remove("checked");
                                    editorr.commit();
                                }



                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("fullname",arrUser.get(0).getName());
                                startActivity(intent);
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
        checkBoxLogin = (CheckBox) findViewById(R.id.checkBoxLogin);
        editTextLoginUsername = (EditText) findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textViewCreatAcc = (TextView) findViewById(R.id.textViewCreatAcc);
    }
}