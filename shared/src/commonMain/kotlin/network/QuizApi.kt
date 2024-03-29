package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class QuizAPI {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Text.Plain,
                json = Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
        }
    }
    suspend fun getAllQuizes(): List<Quiz> {
        val quizList = listOf(
            "https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz.json",
            "https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz2.json",
            "https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz3.json"
        )
        val quizes = quizList.map { url ->
            try {
                val quiz = httpClient.get(url).body<Quiz>()
                quiz
            } catch (e: Exception) {
                null
            }
        }
        return quizes.filterNotNull()
    }
}