package com.example.pyi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    //@Headers("app/json")

    //로그인
    @POST("/login")
    fun userLogin(@Body jsonParams: ApiService.UserModel): Call<ApiService.LoginBackendResponse>

    // 메인 페이지에서 전체 폴더 리스트 조회
    @GET("/mainpage/{userUuid}")
    fun getfolderInfo(@Path("userUuid") userUuid: Long): Call<List<ApiService.folderInfoResponse>>

    // 메인 페이지에서 전체  퀵메모 리스트 조회
    @GET("/viewfolderquickmemolist/{folderUuid}")
    fun getmemoInfo(@Path("folderUuid") folderUuid: Long): Call<List<ApiService.QuickMemo>>

    @GET("summary/{memoUuid}")
    fun getSummaryInfo(@Path("memoUuid") memoUuid: Int): Call<ApiService.MemoDetails>


    companion object {
        private const val BASE_URL = "http://172.30.1.95:8080/"
        val gson : Gson =   GsonBuilder().setLenient().create();

        fun create() : Api{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }
}