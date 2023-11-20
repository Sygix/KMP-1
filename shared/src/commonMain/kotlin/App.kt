import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import network.data.Answer
import network.data.Question
import network.data.Quiz
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {

    var quiz = Quiz(
        listOf(
            Question(
                id = 1,
                label = "What is the name of the framework used to create this app ?",
                answers = listOf(
                    Answer(id = 1, label = "Jetpack Compose"),
                    Answer(id = 2, label = "Flutter"),
                    Answer(id = 3, label = "React Native"),
                    Answer(id = 4, label = "SwiftUI")
                ),
                correctAnswerId = 1
            ),
            Question(
                id = 2,
                label = "What is the name of the language used to create this app ?",
                answers = listOf(
                    Answer(id = 1, label = "Kotlin"),
                    Answer(id = 2, label = "Java"),
                    Answer(id = 3, label = "Swift"),
                    Answer(id = 4, label = "Dart")
                ),
                correctAnswerId = 1
            ),
            Question(
                id = 3,
                label = "What is the name of the platform used to create this app ?",
                answers = listOf(
                    Answer(id = 1, label = "Android"),
                    Answer(id = 2, label = "iOS"),
                    Answer(id = 3, label = "Desktop"),
                    Answer(id = 4, label = "All of them")
                ),
                correctAnswerId = 4
            )
        )
    )

    MaterialTheme {
        //WelcomeScreen()
        //ScoreScreen("10/10")
        QuestionScreen(quiz)
    }
}

expect fun getPlatformName(): String