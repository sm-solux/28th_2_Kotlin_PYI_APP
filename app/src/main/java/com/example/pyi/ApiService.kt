package com.example.pyi

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path
import java.time.LocalDateTime

interface ApiService {
    companion object {
        //자꾸 바뀌는데??
        const val BASE_URL = "http://172.30.1.95:8080/"
    }



    //백엔드로 전송할 데이터 클래스
    data class UserModel(
        @SerializedName("userId")
        var userId : String ?= null,

        @SerializedName("password")
        var password : String ?= null
    )

    //백엔드에서 받는 데이터 클래스
    data class LoginBackendResponse(
        val code : String,
        //200: 성공, 300,400: 에러 백에 요청해서 넣어달라 하기!!
        val userId : String,
        val password : String,
        val userUuid : String //백에 요청해서 넣어달라 하기!!
    )



    data class folderInfoResponse(
        @SerializedName("userUuid")
        val userUuid: Long,

        @SerializedName("folders")
        val folders: List<Folder>
    )

    data class Folder(
        @SerializedName("folderUuid")
        val folderUuid: Long,

        @SerializedName("folderName")
        val folderName: String,

        @SerializedName("folderCreated")
        val folderCreated: String, // 날짜 형식의 문자열로 받습니다

        @SerializedName("memos")
        val memos: List<Memo>
    )

    data class Memo(
        @SerializedName("memoUuid")
        val memoUuid: Long,

        @SerializedName("memoTitle")
        val memoTitle: String,

        @SerializedName("memoDetails")
        val memoDetails: String,

        @SerializedName("memoCreated")
        val memoCreated: String // 날짜 형식의 문자열로 받습니다
    )
//memoInfoResponse

    data class QuickMemo(
        @SerializedName("memoUuid")
        val memoUuid: Long,

        @SerializedName("folderUuid")
        val folderUuid: Long,

        @SerializedName("memoTitle")
        val memoTitle: String,

        @SerializedName("memoDetails")
        val memoDetails: String,

        @SerializedName("memoCreated")
        val memoCreated: String
    )

    //puzzlebutton
    data class MemoDetails(
        @SerializedName("memoUuid")
        val memoUuid: Int,

        @SerializedName("memoTitle")
        val memoTitle: String,

        val keywords: List<Keyword>,
        val todos: List<Todo>,
        val organize: List<Organize>,
        val review: List<Review>,
        val star: List<Star>
    )

    data class Keyword(
        @SerializedName("keywordUuid")
        val keywordUuid: Int,

        @SerializedName("keywordDetails")
        val keywordDetails: String,

        @SerializedName("keywordCreated")
        val keywordCreated: String
    )

    data class Todo(
        @SerializedName("todoUuid")
        val todoUuid: Int,

        @SerializedName("todoDate")
        val todoDate: String,

        @SerializedName("todoDetails")
        val todoDetails: String
    )

    data class Organize(
        @SerializedName("organizeUuid")
        val organizeUuid: Int,

        @SerializedName("organizeTitle")
        val organizeTitle: String,

        @SerializedName("organizeDetails")
        val organizeDetails: String,

        @SerializedName("organizeCreated")
        val organizeCreated: String
    )

    data class Review(
        @SerializedName("reviewUuid")
        val reviewUuid: Int,

        @SerializedName("reviewTitle")
        val reviewTitle: String,

        @SerializedName("reviewDetails")
        val reviewDetails: String,

        @SerializedName("reviewCreated")
        val reviewCreated: String
    )

    data class Star(
        @SerializedName("starUuid")
        val starUuid: Int,

        @SerializedName("starDetails")
        val starDetails: Double
    )


//-------------------------------------------------------------------------------------//



    //자료정리및 구체화(나중에 수정하기!!)
    @GET("vieworganize/1")
    suspend fun getIdeas(): Idea2 // Idea 클래스에 맞게 수정

}