package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import network.data.Quiz

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
    suspend fun getQuiz(): Quiz {
        return httpClient.get("https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz.json").body()
    }

    suspend fun getRandomQuiz(): Quiz {
        val quizList = listOf(
            "https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz.json",
            "https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz2.json",
            "https://raw.githubusercontent.com/Sygix/KMP-1/main/quiz/pokeQuiz3.json"
        )
        val randomQuiz = quizList.random();
        return httpClient.get(randomQuiz).body()
    }
}