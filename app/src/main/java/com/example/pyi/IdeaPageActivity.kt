//IdeaPageActivity.kt
//아이디어 구체화 전단계 페이지
package com.example.pyi

import APIClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime


class IdeaPageActivity : AppCompatActivity() {
    private val apiClient = APIClient(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ideapage)

        // XML 파일에서 정의한 버튼을 찾습니다.
        val myButton: Button = findViewById(R.id.myButton)

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
                            // 전체보기
                            //Log.d("MemoDetails", it.toString())

                            // MemoDetails에서 memoUuid 추출
                            val memoUuid: Int = it.memoUuid

                            // MemoDetails intent로 받아오기


                            // MemoDetails에서 memoTitle 추출
                            val memoTitle: String = it.memoTitle
                            val memoTitleTextView: TextView = findViewById(R.id.title)
                            memoTitleTextView.text = memoTitle

                            // Organize 객체들의 organizeTitle 추출
                            val organizeTitles: List<String> = it.organize.map { organize -> organize.organizeTitle }
                            // 추출된 organizeTitles 활용하여 필요한 작업 수행
                            for ((index, organizeTitle) in organizeTitles.withIndex()) {
                                Log.d("OrganizeTitle", organizeTitle)

                                // organizeTitle을 가져와서 TextView에 설정하는 등의 작업을 추가할 수 있습니다.
                                // 예를 들어, index에 따라서 organize1, organize2 등에 넣을 수 있습니다.
                                when (index) {
                                    0 -> {
                                        val organize1TextView: TextView =
                                            findViewById(R.id.organize1)
                                        organize1TextView.text = organizeTitle
                                    }

                                    1 -> {
                                        val organize2TextView: TextView =
                                            findViewById(R.id.organize2)
                                        organize2TextView.text = organizeTitle
                                    }
                                    // 추가적인 index에 따른 처리도 필요하다면 계속해서 작성할 수 있습니다.
                                    // ...
                                }
                            }

                            // Review 객체들의 속성들 추출
                            val reviewTitles: List<String> = it.review.map { review -> review.reviewTitle }
                            val reviewDetails: List<String> = it.review.map { review -> review.reviewDetails }
                            val reviewCreated: List<String> = it.review.map { review -> review.reviewCreated }

                            // 추출된 reviewTitles, reviewDetails, reviewCreated 활용하여 필요한 작업 수행
                            for (i in reviewTitles.indices) {
                                Log.d("ReviewTitle", reviewTitles[i])
                                Log.d("ReviewDetails", reviewDetails[i])
                                Log.d("ReviewCreated", reviewCreated[i])

                                // 예를 들어, 각각의 정보를 가져와서 TextView에 설정하는 등의 작업을 추가할 수 있습니다.
                                val reviewTitleTextView: TextView = findViewById(R.id.reviewtitle)
                                reviewTitleTextView.text = reviewTitles[i]

                                val reviewDetailsTextView: TextView = findViewById(R.id.reviewdetails)
                                reviewDetailsTextView.text = reviewDetails[i]

                                val reviewCreatedTextView: TextView = findViewById(R.id.reviewdate)
                                reviewCreatedTextView.text = reviewCreated[i]
                            }

                            val starDetail: Double = it.star[0].starDetails
                            val reviewStar: RatingBar = findViewById(R.id.ratingBar)
                            reviewStar.rating = starDetail.toFloat()

                            // 추출된 starDetail 활용하여 필요한 작업 수행
                            Log.d("StarDetails", starDetail.toString())

                            // 추출된 memoTitle 활용하여 필요한 작업 수행
                            Log.d("Extracted MemoTitle", memoTitle)

                            // 예를 들어, memoTitle을 가져와서 TextView에 설정하는 등의 작업을 추가할 수 있습니다.
                            //val memoTitleTextView: TextView = findViewById(R.id.memoTitleTextView)
                            //memoTitleTextView.text = memoTitle
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


        // 버튼에 클릭 리스너를 설정합니다.
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // 버튼 클릭 시 다른 액티비티로 이동하는 코드
                val intent = Intent(this@IdeaPageActivity, IdeaFleshOutActivity::class.java)
                intent.putExtra("memoUUid", memoUuid)
                startActivity(intent)

                // 현재 액티비티에서 토스트 메시지 표시
                Toast.makeText(applicationContext, "다른 페이지로 이동합니다!", Toast.LENGTH_SHORT).show()
            }
        })


        //-----------------------------------------------------------------------------------

        //ReadIdeaJson.kt파일에서 정의한 readJsonFile함수를 통해 gson객체로 Json파일을 읽어들여 ideas에 저장합니다.
        //val ideas = readJsonFile(this, "Test.json")

        //json읽기 테스트 버튼을 찾습니다.
        //val testButton: Button = findViewById(R.id.button2)

        //testButton.setOnClickListener(object : View.OnClickListener {
            //override fun onClick(view: View) {
                // CoroutineScope 내에서 비동기로 HTTP 요청을 수행
                //CoroutineScope(Dispatchers.IO).launch {
                    //try {
                        // Get the list of ideas from the API
                        //val idea: Idea2? = apiClient.getIdeas()

                        // UI 스레드에서 Toast를 표시
                        //withContext(Dispatchers.Main) {
                            // idea가 null이 아닌 경우에만 실행
                            //idea?.let {
                                // 읽어들인 JSON 데이터 활용하여 메시지 생성
                                //val message = "사용자id: ${it.organizeUuid}\n" +
                                        //"메모id: ${it.memoUuid}\n" +
                                        //"할일 제목: ${it.organizeTitle}\n" +
                                        //"할일 내용: ${it.organizeDetails}\n" +
                                        //"작성 시간: ${it.organizeCreated}"

                                // Toast로 메시지 출력
                                //Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                            //}
                        //}

                    //} catch (e: Exception) {
                        // 오류 처리
                        //e.printStackTrace()
                    //}
                //}
            //}
        //})
        //---------------------------------------------------------------------------------------

    }
}