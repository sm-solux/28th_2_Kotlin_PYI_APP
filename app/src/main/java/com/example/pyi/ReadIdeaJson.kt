//ReadIdeaJson.kt
// Gson 라이브러리를 사용하여 JSON 파일을 읽기 위한 함수
package com.example.pyi

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.InputStreamReader

// IdeasContainer 클래스는 JSON 파일의 최상위 객체로, 여러 Idea 객체를 포함합니다.
data class IdeasContainer(val Ideas: Array<Idea>)

// Idea 클래스는 각각의 아이디어를 나타냅니다. memos는 메모, keywords는 키워드 목록, todos는 할 일 목록입니다.
data class Idea(val memos: String, val keywords: List<String>, val todos: List<List<String>>)

// JSON 파일을 읽기 위한 함수
fun readJsonFile(context: Context, fileName: String): Array<Idea>? {
    // Gson 객체 생성
    val gson = Gson()

    return try {
        // assets 폴더에서 Test.json 파일을 읽기
        val inputStream = context.assets.open("Test.json")
        val reader = InputStreamReader(inputStream)

        // Gson을 사용하여 JSON 파일을 IdeasContainer 객체로 변환
        val ideasContainer = gson.fromJson(reader, IdeasContainer::class.java)
        reader.close()

        // 성공적으로 JSON 파일을 읽었을 때 로그 출력
        Log.d("JSONRead", "Successfully read JSON file.")

        // IdeasContainer 객체에서 Ideas 배열을 반환
        ideasContainer.Ideas
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}