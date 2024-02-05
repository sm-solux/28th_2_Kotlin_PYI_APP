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
        const val BASE_URL = "http://10.101.58.15:8080/"
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


//-------------------------------------------------------------------------------------//



    //자료정리및 구체화(나중에 수정하기!!)
    @GET("vieworganize/1")
    suspend fun getIdeas(): Idea2 // Idea 클래스에 맞게 수정

}