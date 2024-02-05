//로그인 페이지
package com.example.pyi

import APIClient
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class LoginActivity : AppCompatActivity() {
    // APIClient 초기화
    private val apiClient = APIClient(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button = findViewById(R.id.loginbutton)

        loginButton.setOnClickListener {
            // 아이디와 비밀번호를 EditText에서 가져오기
            val usernameEditText: EditText = findViewById(R.id.usernameEditText)
            val passwordEditText: EditText = findViewById(R.id.passwordEditText)

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            Log.d("UserInput", "Username: $username, Password: $password")

            val api = Api.create()
            val data = ApiService.UserModel(username, password)

            api.userLogin(data).enqueue(object : Callback<ApiService.LoginBackendResponse> {
                override fun onResponse(
                    call: Call<ApiService.LoginBackendResponse>,
                    response: Response<ApiService.LoginBackendResponse>
                ) {
                    Log.d("로그인 통신 성공", response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())

                    when (response.code()) {
                        200 -> {
                            // 성공 응답 처리
                            Toast.makeText(this@LoginActivity, "로그인 성공!", Toast.LENGTH_LONG).show()
                            startMainActivity(1) //백에 요청한다음 변수로 수정하기!!

                        }
                        405 -> Toast.makeText(
                            this@LoginActivity,
                            "로그인 실패 : 아이디나 비밀번호가 올바르지 않습니다",
                            Toast.LENGTH_LONG
                        ).show()
                        500 -> Toast.makeText(this@LoginActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ApiService.LoginBackendResponse>, t: Throwable) {
                    // 실패
                    Log.d("로그인 통신 실패", t.message.toString())
                    Log.d("로그인 통신 실패", "fail")
                }
            })

        }


    }

    // MainActivity로 이동하는 함수
    private fun startMainActivity(userUuid: Long) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userUuid", userUuid)
        startActivity(intent)
    }
}
