
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import network.data.Answer
import network.data.Question
import network.data.Quiz

@Composable
internal fun rootNavHost() {

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

    val navigator = rememberNavigator()

    NavHost(
        navigator = navigator,
        navTransition = NavTransition(
            createTransition = slideInHorizontally(initialOffsetX = { it }),
            destroyTransition = slideOutHorizontally(targetOffsetX = { -it }),
            resumeTransition = slideInHorizontally(initialOffsetX = { it }),
            pauseTransition = slideOutHorizontally(targetOffsetX = { -it }),
            enterTargetContentZIndex = 1f,
            exitTargetContentZIndex = 1f,
        ),
        initialRoute = "/start"
    ) {
        scene(
            route = "/start",
        ) {
            StartScreen(navigator)
        }
        scene(
            route = "/quiz",
        ) {

            if (quiz.questions.isNotEmpty()) {
                QuizScreen(navigator, quiz)
            }
        }
        scene(
            route = "/score/{score}",
        ) { backStackEntry ->
            backStackEntry.path<String>("score")?.let { score ->
                ScoreScreen(navigator, score)
            }
        }
    }

}