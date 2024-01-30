package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //json파일 읽는 함수
        getJson()
        val ideas = readJsonFile(this, "Test.json")


        // XML 파일에서 정의한 버튼을 찾습니다.
        val myButton: Button = findViewById(R.id.myButton)

        // 버튼에 클릭 리스너를 설정합니다.
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // 버튼 클릭 시 다른 액티비티로 이동하는 코드
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)

                // 현재 액티비티에서 토스트 메시지 표시
                Toast.makeText(applicationContext, "다른 페이지로 이동합니다!", Toast.LENGTH_SHORT).show()
            }
        })

        val testButton: Button = findViewById(R.id.button2)

        // 버튼에 클릭 리스너를 설정합니다.
        testButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val message = "Memos:Keywords: "
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                ideas?.forEach { idea ->
                    // 아이디어 데이터 활용
                    val message = "Memos: ${idea.memos}\nKeywords: ${idea.keywords.joinToString(", ")}\nTodos: ${idea.todos.joinToString(", ") { it.joinToString(" - ") }}"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun getJson() {
        try {
            //test.json이라는 json파일을 읽는다.
            val inputStream = assets.open("Test.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = null
            while (true) {
                line = reader.readLine()
                Log.d("Json", "line : $line")
                if (line == null) {
                    break
                }
            }
            reader.close()
        } catch (e: Exception) {
        }
    }
}