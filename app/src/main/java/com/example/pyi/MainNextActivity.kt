package com.example.pyi

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pyi.databinding.ActivityMainNextBinding
import androidx.appcompat.widget.Toolbar
import com.example.pyi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainNextActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val folderAdapter = MyAdapter {
            // 폴더 버튼 클릭 처리, 예를 들어 다른 페이지로 이동
            val intent = Intent(this, MainNextActivity::class.java)
            startActivity(intent)
        }
        val memoAdapter = MyAdapter2 { memoTitle, memoDetails, position, memoCreated ->
            // 퀵메모 리스트 클릭 처리, 예를 들어 다른 페이지로 이동
            val intent = Intent(this, QuickMemoActivity::class.java)
            intent.putExtra("memoTitle", memoTitle)
            intent.putExtra("memoDetails", memoDetails)
            intent.putExtra("memoUUid", position)
            intent.putExtra("memoCreated", memoCreated)
            startActivity(intent)
        }




        binding.folderRecyclerview.adapter = folderAdapter

        // Intent로 전달된 데이터 확인
        //userUuid로 회원 아이디
        val userUuid = intent.getLongExtra("userUuid",-1L)
        // userUuid를 사용하기 전에 null 체크를 수행하거나, 기본값을 지정할 수 있습니다.
        if (userUuid != -1L) {
            Log.d("UserUuid", userUuid.toString())
            // userUuid를 사용하여 필요한 작업 수행
            val api = Api.create()

            // Retrofit을 사용하여 유저 정보 가져오기
            api.getfolderInfo(userUuid).enqueue(object : Callback<List<ApiService.folderInfoResponse>> {
                override fun onResponse(
                    call: Call<List<ApiService.folderInfoResponse>>,
                    response: Response<List<ApiService.folderInfoResponse>>
                ) {
                    if (response.isSuccessful) {
                        val folderInfoResponse = response.body()
                        val folderUuids: List<Long> = response.body()?.flatMap { it.folders }?.map { it.folderUuid } ?: emptyList()
                        val folderNames: List<String> = response.body()?.flatMap { it.folders }?.map { it.folderName } ?: emptyList()
                        //Log.d("통신 성공", response.body().toString())
                        //Log.d("폴더명", folderNames.toString())
                        // 어댑터에 데이터 전달
                        folderAdapter.setData(folderNames,folderUuids)

                    } else {
                        // 실패한 경우
                        //Log.d("응답 코드", response.code().toString())
                        //Log.d("통신 실패", "응답 바디: ${response.errorBody()?.string()}")

                    }
                }

                override fun onFailure(call: Call<List<ApiService.folderInfoResponse>>, t: Throwable) {
                    // 통신 실패 시의 처리
                    //Log.d("통신 실패", "onFailure: ${t.message}")
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


        //---------------------------퀵메모 조회---------------------------//
        val api2 = Api.create()
        val folderUuid = 1L
        // Retrofit을 사용하여 유저 정보 가져오기
        api2.getmemoInfo(folderUuid).enqueue(object : Callback<List<ApiService.QuickMemo>> {
            override fun onResponse(
                call: Call<List<ApiService.QuickMemo>>,
                response: Response<List<ApiService.QuickMemo>>
            ) {
                if (response.isSuccessful) {
                    //val memoInfoResponse = response.body()
                    //val folderNames: List<String> = response.body()?.flatMap { it.folders }?.map { it.folderName } ?: emptyList()
                    Log.d("통신 성공", response.body().toString())
                    //Log.d("폴더명", folderNames.toString())
                    // 어댑터에 데이터 전달
                    //folderAdapter.setData(folderNames)
                    val quickMemos = response.body()
                    val memoTitles: List<String> = quickMemos?.map { it.memoTitle } ?: emptyList()
                    val memoDetails: List<String> = quickMemos?.map { it.memoDetails } ?: emptyList()
                    val memoCreated: List<String> = quickMemos?.map { it.memoCreated } ?: emptyList()

                    // 어댑터에 메모 데이터 전달
                    memoAdapter.setData(memoTitles, memoDetails, memoCreated)

                } else {
                    // 실패한 경우
                    Log.d("응답 코드", response.code().toString())
                    Log.d("통신 실패", "응답 바디: ${response.errorBody()?.string()}")

                }
            }
            override fun onFailure(call: Call<List<ApiService.QuickMemo>>, t: Throwable) {
                // 통신 실패 시의 처리
                Log.d("통신 실패", "onFailure: ${t.message}")
            }
        })


        val layoutManeger2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManeger.stackFromEnd = true
        binding.memoRecyclerview.layoutManager = layoutManeger2
        //binding.memoRecyclerview.adapter = MyAdapter2()



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