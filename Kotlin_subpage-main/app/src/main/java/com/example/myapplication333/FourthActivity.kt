package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FourthActivity : AppCompatActivity() {

    private lateinit var contentEditText: EditText
    private lateinit var editButton: ImageButton

    private var isEditingMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        contentEditText = findViewById(R.id.contentEditText)
        val deleteButton: ImageButton = findViewById(R.id.deleteButton)
        val backButton: ImageButton = findViewById(R.id.backButton)
        editButton = findViewById(R.id.editButton)

        // 초기에는 수정 불가능한 상태로 설정
        contentEditText.isEnabled = false

        deleteButton.setOnClickListener {
            // "삭제" 버튼이 클릭되었을 때 EditText의 내용을 모두 삭제
            contentEditText.text.clear()
        }

        backButton.setOnClickListener {
            // 뒤로가기 버튼이 클릭되었을 때 현재 액티비티를 종료하여 뒤로가기 동작 실행
            finish()
        }

        editButton.setOnClickListener {
            // 수정 버튼이 클릭되었을 때
            isEditingMode = !isEditingMode

            // 편집 모드인 경우 EditText를 편집 가능한 상태로 설정, 아닌 경우 편집 불가능한 상태로 설정
            contentEditText.isEnabled = isEditingMode
        }

    }



    override fun onBackPressed() {
        // 뒤로가기 버튼이 눌렸을 때 실행되는 메서드
        super.onBackPressed()
    }
}