import androidx.compose.material.Card
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
                label = "What is the name of the platform where this app can be deployed ?",
                answers = listOf(
                    Answer(id = 1, label = "Android"),
                    Answer(id = 2, label = "iOS"),
                    Answer(id = 3, label = "Desktop"),
                    Answer(id = 4, label = "All of them")
                ),
                correctAnswerId = 4
            ),
            Question(
                id = 4,
                label = "What is the name of the authors of this app ?",
                answers = listOf(
                    Answer(id = 1, label = "Elon & Jeff"),
                    Answer(id = 2, label = "Steve & Jobs"),
                    Answer(id = 3, label = "Bill & Gates"),
                    Answer(id = 4, label = "Simon & Rafael")
                ),
                correctAnswerId = 1
            ),
            Question(
                id = 5,
                label = "What is the name of the school where this app was created ?",
                answers = listOf(
                    Answer(id = 1, label = "42"),
                    Answer(id = 2, label = "Epitech"),
                    Answer(id = 3, label = "Efficom"),
                    Answer(id = 4, label = "Modart")
                ),
                correctAnswerId = 3
            ),
        )
    )

    MaterialTheme {
        //WelcomeScreen()
        //ScoreScreen("10/10")
        QuestionScreen(quiz)
    }
}

expect fun getPlatformName(): String