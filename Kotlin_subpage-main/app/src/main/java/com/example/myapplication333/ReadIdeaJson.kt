package com.example.myapplication333

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.InputStreamReader

data class IdeasContainer(val Ideas: Array<Idea>)

data class Idea(val memos: String, val keywords: List<String>, val todos: List<List<String>>)

fun readJsonFile(context: Context, fileName: String): Array<Idea>? {
    // Gson 객체 생성
    val gson = Gson()

    return try {
        // assets 폴더에서 파일을 읽기
        val inputStream = context.assets.open("Test.json")
        val reader = InputStreamReader(inputStream)
        val ideasContainer = gson.fromJson(reader, IdeasContainer::class.java)
        reader.close()

        // 성공적으로 JSON 파일을 읽었을 때 로그 출력
        Log.d("JSONRead", "Successfully read JSON file.")

        ideasContainer.Ideas
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}



