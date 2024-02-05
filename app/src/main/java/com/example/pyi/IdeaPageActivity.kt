//IdeaPageActivity.kt
//아이디어 구체화 전단계 페이지
package com.example.pyi

import APIClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import kotlinx.coroutines.NonCancellable.message
import java.io.BufferedReader
import java.io.InputStreamReader
import okhttp3.logging.HttpLoggingInterceptor
import java.time.LocalDateTime


class IdeaPageActivity : AppCompatActivity() {
    private val apiClient = APIClient(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ideapage)

        // XML 파일에서 정의한 버튼을 찾습니다.
        val myButton: Button = findViewById(R.id.myButton)

        // 버튼에 클릭 리스너를 설정합니다.
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // 버튼 클릭 시 다른 액티비티로 이동하는 코드
                val intent = Intent(this@IdeaPageActivity, IdeaFleshOutActivity::class.java)
                startActivity(intent)

                // 현재 액티비티에서 토스트 메시지 표시
                Toast.makeText(applicationContext, "다른 페이지로 이동합니다!", Toast.LENGTH_SHORT).show()
            }//$2a$10$XGoHIn3Sd9kaSKYLVkuUoOB6Wkt6X48h24b0vWH1Gaankw30lUgWW
             //$2a$10$XGoHIn3Sd9kaSKYLVkuUoOB6Wkt6X48h24b0vWH1Gaankw30lUgWW
        })


        //ReadIdeaJson.kt파일에서 정의한 readJsonFile함수를 통해 gson객체로 Json파일을 읽어들여 ideas에 저장합니다.
        //val ideas = readJsonFile(this, "Test.json")

        //-----------------------------------------------------------------------------------
        //json읽기 테스트 버튼을 찾습니다.
        val testButton: Button = findViewById(R.id.button2)

        testButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // CoroutineScope 내에서 비동기로 HTTP 요청을 수행
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        // Get the list of ideas from the API
                        val idea: Idea2? = apiClient.getIdeas()

                        // UI 스레드에서 Toast를 표시
                        withContext(Dispatchers.Main) {
                            // idea가 null이 아닌 경우에만 실행
                            idea?.let {
                                // 읽어들인 JSON 데이터 활용하여 메시지 생성
                                val message = "사용자id: ${it.organizeUuid}\n" +
                                        "메모id: ${it.memoUuid}\n" +
                                        "할일 제목: ${it.organizeTitle}\n" +
                                        "할일 내용: ${it.organizeDetails}\n" +
                                        "작성 시간: ${it.organizeCreated}"

                                // Toast로 메시지 출력
                                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                            }
                        }

                    } catch (e: Exception) {
                        // 오류 처리
                        e.printStackTrace()
                    }
                }
            }
        })
        //---------------------------------------------------------------------------------------

    }
}