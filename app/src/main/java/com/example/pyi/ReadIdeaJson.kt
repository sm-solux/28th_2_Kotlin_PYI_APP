//ReadIdeaJson.kt
// Gson 라이브러리를 사용하여 JSON 파일을 읽기 위한 함수
package com.example.pyi

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.InputStreamReader
import java.time.LocalDateTime
import com.google.gson.annotations.SerializedName
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.format.DateTimeFormatter

data class Idea2(
    @SerializedName("organizeUuid") val organizeUuid: Long,
    @SerializedName("memoUuid") val memoUuid: Long,
    @SerializedName("organizeTitle") val organizeTitle: String,
    @SerializedName("organizeDetails") val organizeDetails: String,
    @SerializedName("organizeCreated") val organizeCreated: LocalDateTime
)

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime {
        val dateString = json.asString
        return LocalDateTime.parse(dateString, formatter)
    }
}