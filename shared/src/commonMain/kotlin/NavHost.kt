
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import network.QuizRepo

val repo = QuizRepo()

@Composable
internal fun rootNavHost() {

    val quizes = repo.quizesState.collectAsState()
    val randomQuiz = repo.randomQuizState.collectAsState()
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
            route = "/quiz/{quizId}",
        ) {backStackEntry ->
            backStackEntry.path<String>("quizId")?.let { quizId ->
                if(quizes.value.find { it.id == quizId.toInt() } != null){
                    QuizScreen(navigator, quizes.value.find { it.id == quizId.toInt() }!!.questions)
                } else {
                    QuizScreen(navigator, quizes.value[0].questions)
                }
            }
        }
        scene(
            route = "/quiz/random",
        ) {
            QuizScreen(navigator, randomQuiz.value)
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