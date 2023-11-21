
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import network.QuizRepo

@Composable
internal fun rootNavHost() {

    val repo = QuizRepo()
    val questions = repo.questionState.collectAsState()
    val randomQuestions = repo.randomQuestionState.collectAsState()
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
            repo.updateRandomQuiz()
            StartScreen(navigator)
        }
        scene(
            route = "/quiz",
        ) {

            if (questions.value.isNotEmpty()) {
                QuizScreen(navigator, questions.value)
            }
        }
        scene(
            route = "/quiz/random",
        ) {
            if (randomQuestions.value.isNotEmpty()) {
                QuizScreen(navigator, randomQuestions.value)
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