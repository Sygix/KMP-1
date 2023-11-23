package network

import com.myapplication.database.DriverFactory
import com.myapplication.database.QuizDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class QuizRepo()  {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private var _quizesState=  MutableStateFlow(listOf<Quiz>())
    var quizesState = _quizesState

    private var _randomQuizState=  MutableStateFlow(listOf<Question>())
    var randomQuizState = _randomQuizState

    init {
        updateQuiz()
    }

    private suspend fun fetQuizesFromApi(): List<Quiz> = QuizAPI().getAllQuizes()
    private fun fetchQuizesFromDatabase(): List<Quiz> = QuizDatabase(DriverFactory()).getQuizes()

    private fun updateQuiz(){
        coroutineScope.launch {
            val quizesFromDatabase = fetchQuizesFromDatabase()
            val now = Clock.System.now().toEpochMilliseconds()
            try {
                if(quizesFromDatabase.isEmpty()){
                    val quizesFromApi = fetQuizesFromApi()
                    for (quiz in quizesFromApi) {
                        QuizDatabase(DriverFactory()).insertQuiz(quiz)
                    }
                    _quizesState.update { quizesFromApi }
                    println("quizesState : ${_quizesState.value}")
                } else {
                    for (quiz in quizesFromDatabase) {
                        val diff = (now - quiz.createdAt) / (1000 * 60)
                        if (diff > 5) { //5mins difference
                            _quizesState.update { fetQuizesFromApi() }
                            return@launch
                        }
                    }
                    _quizesState.update { quizesFromDatabase }
                }
            } catch (e: Exception) {
                _quizesState.update { quizesFromDatabase }
            }
        }
    }

    fun updateRandomQuiz() {
        coroutineScope.launch {
            val quizes = _quizesState.value
            println("quizes: $quizes")
            if(quizes.isEmpty()) return@launch
            val randomQuiz = quizes.random()
            _randomQuizState.update { randomQuiz.questions }
        }
    }
}