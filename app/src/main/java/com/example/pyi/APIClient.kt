import android.app.Activity
import android.content.Intent
import com.example.pyi.ApiService
import com.example.pyi.Idea2
import com.example.pyi.LocalDateTimeDeserializer
import com.example.pyi.MainActivity
import com.example.pyi.RegistrationRequest
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class APIClient(private val activity: Activity) {
    private val apiService: ApiService

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    suspend fun getIdeas(): Idea2? {
        return try {
            apiService.getIdeas()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
