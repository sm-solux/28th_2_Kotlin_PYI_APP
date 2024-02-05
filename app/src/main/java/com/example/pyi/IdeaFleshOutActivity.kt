//아이디어 구체화 메인 페이지
package com.example.pyi

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*


class IdeaFleshOutActivity : AppCompatActivity() {

    private lateinit var writeButtonSecond: AppCompatButton
    private lateinit var containerLayout: LinearLayout

    private lateinit var dateTextView1: TextView
    private lateinit var dateTextView2: TextView
    private lateinit var dateTextView3: TextView

    private lateinit var titleTextView1: TextView
    private lateinit var titleTextView2: TextView
    private lateinit var titleTextView3: TextView

    private lateinit var titleTextViewList: List<TextView>

    private lateinit var ratingBar: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ideafleshout)

        //쿼리를 위한 메모아이디 불러온다
        var memoUuid = intent.getIntExtra("memoUUid",0)

        // userUuid를 사용하기 전에 null 체크를 수행하거나, 기본값을 지정할 수 있습니다.
        if (memoUuid != 0) {
            Log.d("UserUuid", memoUuid.toString())
            // userUuid를 사용하여 필요한 작업 수행
            val api = Api.create()

            // Retrofit을 사용하여 유저 정보 가져오기
            api.getSummaryInfo(memoUuid).enqueue(object :
                Callback<ApiService.MemoDetails> {
                override fun onResponse(
                    call: Call<ApiService.MemoDetails>,
                    response: Response<ApiService.MemoDetails>
                ) {
                    if (response.isSuccessful) {
                        val summarys = response.body()

                        summarys?.let { // summarys가 null이 아닌 경우에만 실행
                            // MemoDetails에서 memoUuid 추출
                            val memoUuid: Int = it.memoUuid




                        }

                    } else {
                        // 실패한 경우
                        Log.d("통신 실패", "응답 코드: ${response.code()}")
                        Log.d("통신 실패", "응답 바디: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<ApiService.MemoDetails>, t: Throwable) {
                    // 통신 실패 시의 처리
                    Log.d("통신 실패", "onFailure: ${t.message}")
                }
            })

        } else {
            // userUuid가 null일 때의 처리
            Log.d("UserUuid", "값이 없습니다.")
        }


        // 딥링크 데이터를 처리하는 부분
        handleDeepLinkData(intent)

        // shareButton 클릭 이벤트 설정
        val shareButton: ImageButton = findViewById(R.id.shareButton)
        shareButton.setOnClickListener {
            shareDeepLink()
        }

        // XML에서 정의한 버튼들을 찾아서 변수에 할당
        writeButtonSecond = findViewById(R.id.write_btn_second)
        containerLayout = findViewById(R.id.tag_container_layout)

        // + 버튼 클릭 이벤트 리스너
        writeButtonSecond.setOnClickListener {
            showTagInputDialog()
        }

        dateTextView1 = findViewById(R.id.date_textview_1)
        dateTextView2 = findViewById(R.id.date_textview_2)
        dateTextView3 = findViewById(R.id.date_textview_3)

        titleTextView1 = findViewById(R.id.title_textview_1)
        titleTextView2 = findViewById(R.id.title_textview_2)
        titleTextView3 = findViewById(R.id.title_textview_3)

        titleTextViewList = listOf(
            findViewById(R.id.title_textview_1),
            findViewById(R.id.title_textview_2),
            findViewById(R.id.title_textview_3)
        )

        // RatingBar 초기화
        ratingBar = findViewById(R.id.ratingBar)

        // RatingBar 리스너 설정
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // 별점 변경 이벤트 처리
            // rating 변수에 사용자가 선택한 별점이 들어 있음
            // 이 값을 사용하여 데이터 저장 등의 작업을 수행할 수 있음
            saveRatingToDatabase(rating)
        }

        val writeButtonM = findViewById<AppCompatButton>(R.id.write_btn)

        // + 버튼 클릭 이벤트 리스너
        writeButtonM.setOnClickListener {

            // 자료정리 및 구체화로 이동
            val intent = Intent(this, DataOrganizationActivity::class.java)
            startActivity(intent)
        }

        val writeButtonF = findViewById<AppCompatButton>(R.id.write_btn_F)

// + 버튼 클릭 이벤트 리스너
        writeButtonF.setOnClickListener {
            // 평가및 정리로 이동
            val intent = Intent(this, EvaluationActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // 액티비티가 이미 실행 중일 때, 새로운 딥링크로 인텐트가 도착할 때 호출되는 메소드
        handleDeepLinkData(intent)
    }

    private fun handleDeepLinkData(intent: Intent?) {
        if (intent != null && intent.action == Intent.ACTION_VIEW) {
            val data: Uri? = intent.data
            if (data != null) {
                // 여기에서 딥링크 데이터를 사용하는 로직을 추가
                val scheme = data.scheme
                val host = data.host
                // 딥링크에 대한 추가 처리...
            }
        }
    }

    private fun shareDeepLink() {
        // 딥링크를 생성합니다. 여기서는 예시로 yourapp://yourhostname을 사용하였습니다.
        val deepLink = "yourapp://yourhostname"

        // 딥링크를 사용하여 공유 인텐트를 생성합니다.
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, deepLink)

        // 공유 인텐트를 시작합니다.
        startActivity(Intent.createChooser(shareIntent, "공유하기"))




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

    private fun saveRatingToDatabase(rating: Float) {
        // 여기에서 데이터베이스에 별점을 저장하는 로직을 구현
        // 예를 들어, Firebase Realtime Database, SQLite, 등을 사용할 수 있음
    }
    fun showTitleEditDialog(view: View) {
        // 다이얼로그 생성
        val builder = AlertDialog.Builder(this)
        builder.setTitle("제목 수정")

        // 현재 선택된 TextView의 ID를 가져오기
        val selectedTitleTextViewId = view.id

        // 현재 선택된 TextView의 인덱스를 찾기
        val selectedTitleTextViewIndex = titleTextViewList.indexOfFirst { it.id == selectedTitleTextViewId }

        if (selectedTitleTextViewIndex != -1) {
            val currentTitleTextView = titleTextViewList[selectedTitleTextViewIndex]
            val currentTitle = currentTitleTextView.text.toString()

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.setText(currentTitle)

            builder.setView(input)

            builder.setPositiveButton("확인") { dialog, which ->
                // 사용자가 입력한 내용으로 제목을 업데이트
                val newTitle = input.text.toString()
                currentTitleTextView.text = newTitle
            }

            builder.setNegativeButton("취소") { dialog, which ->
                dialog.cancel()
            }

            builder.show()
        }
    }



    fun showDatePickerDialog(view: View) {
        // 현재 선택된 dateTextView의 ID를 가져오기
        val selectedDateTextViewId = view.id

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, day: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)
                updateDateTextView(selectedDate.time, selectedDateTextViewId)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }


    private fun updateDateTextView(date: Date, dateTextViewId: Int) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.format(date)

        // 현재 선택된 dateTextView의 ID를 가져오기
        val currentDateTextViewId = dateTextViewId

        // 현재 선택된 dateTextView를 찾아서 업데이트
        val currentDateTextView = findViewById<TextView>(currentDateTextViewId)
        currentDateTextView.text = formattedDate
    }


    private fun showTagInputDialog() {
        // 다이얼로그 생성
        val builder = AlertDialog.Builder(this)
        builder.setTitle("태그 추가")

        // 입력란 설정
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // 버튼 설정
        builder.setPositiveButton("추가") { dialog, which ->
            val enteredTag = input.text.toString()
            addTagButton(enteredTag)
        }

        builder.setNegativeButton("취소") { dialog, which ->
            dialog.cancel()
        }

        // 다이얼로그 표시
        builder.show()
    }

    private fun addTagButton(tag: String) {
        val newTagButton = AppCompatButton(this)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // 패딩 및 마진 설정
        params.setMargins(0, -5, 25, 0)  // 오른쪽 마진 10dp로 설정
        newTagButton.layoutParams = params

        newTagButton.text = tag
        newTagButton.setBackgroundResource(R.drawable.custom_background)
        newTagButton.elevation = 15f
        newTagButton.textSize = 10f

        containerLayout.addView(newTagButton)
    }

}