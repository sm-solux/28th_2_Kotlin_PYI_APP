//IdeaPageActivity.kt
//아이디어 구체화 전단계 페이지
package com.example.pyi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.coroutines.NonCancellable.message
import java.io.BufferedReader
import java.io.InputStreamReader

class IdeaPageActivity : AppCompatActivity() {
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
            }
        })


        //ReadIdeaJson.kt파일에서 정의한 readJsonFile함수를 통해 gson객체로 Json파일을 읽어들여 ideas에 저장합니다.
        val ideas = readJsonFile(this, "Test.json")

        //json읽기 테스트 버튼을 찾습니다.
        val testButton: Button = findViewById(R.id.button2)

        //테스트 버튼 클릭시 ideas의 json 데이터를 Toast로 앱에 출력합니다.
        testButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // ideas가 null이 아닌 경우에만 실행
                ideas?.forEach { idea ->
                    // 읽어들인 JSON 데이터 활용하여 메시지 생성
                    val message = "Memos: ${idea.memos}\nKeywords: ${idea.keywords.joinToString(", ")}\nTodos: ${idea.todos.joinToString(", ") { it.joinToString(" - ") }}"
                    // Toast로 메시지 출력
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}