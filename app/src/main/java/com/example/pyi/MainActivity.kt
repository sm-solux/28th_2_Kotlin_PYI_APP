//메인페이지
package com.example.pyi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pyi.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response



class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // Intent로 전달된 데이터 확인
        //userUuid로 회원 아이디
        val userUuid = intent.getLongExtra("userUuid",-1L)
        // userUuid를 사용하기 전에 null 체크를 수행하거나, 기본값을 지정할 수 있습니다.
        if (userUuid != -1L) {
            Log.d("UserUuid", userUuid.toString())
            // userUuid를 사용하여 필요한 작업 수행
            val api = Api.create()

            // Retrofit을 사용하여 유저 정보 가져오기
            api.getfolderInfo(userUuid).enqueue(object : Callback<ApiService.folderInfoResponse> {
                override fun onResponse(
                    call: Call<ApiService.folderInfoResponse>,
                    response: Response<ApiService.folderInfoResponse>
                ) {
                    if (response.isSuccessful) {
                        val folderInfoResponse = response.body()
                        if (folderInfoResponse != null && folderInfoResponse.folders.isNotEmpty()) {
                            val firstFolder = folderInfoResponse.folders[0]
                            // 첫 번째 폴더에 대한 처리
                            Log.d("통신 성공", "첫 번째 폴더 이름: ${firstFolder.folderName}")
                        } else {
                            // 유효한 폴더 정보가 없는 경우의 처리
                            Log.d("통신 실패", "폴더 정보가 없습니다.")
                        }
                    } else {
                        // 실패한 경우
                        Log.d("응답 코드", response.code().toString())
                        Log.d("통신 실패", "응답 바디: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<ApiService.folderInfoResponse>, t: Throwable) {
                    // 통신 실패 시의 처리
                    Log.d("통신 실패", "onFailure: ${t.message}")
                }
            })

        } else {
            // userUuid가 null일 때의 처리
            Log.d("UserUuid", "값이 없습니다.")
        }


        setSupportActionBar(binding.toolbar)

        toggle= ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()


        val layoutManeger = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManeger.stackFromEnd = true
        binding.folderRecyclerview.layoutManager = layoutManeger


        val folderAdapter = MyAdapter {
            // 폴더 버튼 클릭 처리, 예를 들어 다른 페이지로 이동
            val intent = Intent(this, MainNextActivity::class.java)
            startActivity(intent)
        }

        binding.folderRecyclerview.adapter = folderAdapter

        val layoutManeger2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManeger.stackFromEnd = true
        binding.memoRecyclerview.layoutManager = layoutManeger2
        //binding.memoRecyclerview.adapter = MyAdapter2()

        val memoAdapter = MyAdapter2 {
            // 메모 버튼 클릭 처리, 예를 들어 다른 페이지로 이동
            val intent = Intent(this, QuickMemoActivity::class.java)
            startActivity(intent)
        }

        binding.memoRecyclerview.adapter = memoAdapter


        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun navigateToOtherActivity() {
        val intent = Intent(this, QuickMemoActivity::class.java)  // OtherActivity는 이동할 대상 액티비티 클래스입니다. 클래스명을 실제 액티비티 클래스명으로 변경하세요.
        startActivity(intent)
    }


}



