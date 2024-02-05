//퀵메모 페이지
package com.example.pyi


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class QuickMemoActivity : AppCompatActivity() {

    private lateinit var contentEditText: EditText
    private lateinit var editButton: ImageButton
    private lateinit var textViewMemoTitle: EditText
    private lateinit var textViewMemoDetail: EditText

    private var isEditingMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quickmemo)

        // 메모 타이틀을 받아오는 코드
        val memoTitle = intent.getStringExtra("memoTitle")
        val memoDetails = intent.getStringExtra("memoDetails")
        var memoUuid = intent.getIntExtra("memoUUid",0)

        memoUuid = memoUuid + 1

        textViewMemoTitle = findViewById(R.id.titleEditText)
        textViewMemoDetail = findViewById(R.id.contentEditText)


        // 메모 타이틀이 null이 아닌 경우에만 화면에 표시
        if (memoTitle != null) {
            // memoTitle이 Nullable일 경우에 대비하여 null 체크 후 변환
            textViewMemoTitle.text = memoTitle?.toString()?.toEditable()
            textViewMemoDetail.text = memoDetails?.toString()?.toEditable()
            Log.d("memoUuid", memoUuid.toString())
        } else {
            Log.d("QuickMemoActivity", "No Memo Title Received")
            Log.d("QuickMemoActivity", "No Memo Details Received")
        }




        contentEditText = findViewById(R.id.contentEditText)
        val deleteButton: ImageButton = findViewById(R.id.deleteButton)
        val backButton: ImageButton = findViewById(R.id.backButton)
        val puzzleButton: ImageButton = findViewById(R.id.puzzleButton)
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

        //퍼즐버튼 눌렀을때
        puzzleButton.setOnClickListener {
            val intent = Intent(this, IdeaPageActivity::class.java)
            intent.putExtra("memoUUid", memoUuid)
            startActivity(intent)
        }
    }


    override fun onBackPressed() {
        // 뒤로가기 버튼이 눌렸을 때 실행되는 메서드
        super.onBackPressed()
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

}
