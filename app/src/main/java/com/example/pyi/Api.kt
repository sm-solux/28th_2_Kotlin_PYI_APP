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

    // 메인 페이지에서 전체 폴더 리스트 퀵메모 리스트 조회
    @GET("/mainpage/{userUuid}")
    fun getfolderInfo(@Path("userUuid") userUuid: Long): Call<ApiService.folderInfoResponse>

    companion object {
        private const val BASE_URL = "http://192.168.45.208:8080/"
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