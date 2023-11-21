package network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.data.Question

class QuizRepo()  {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private var _questionState=  MutableStateFlow(listOf<Question>())
    var questionState = _questionState

    private var _randomQuestionState=  MutableStateFlow(listOf<Question>())
    var randomQuestionState = _randomQuestionState

    init {
        updateQuiz()
        updateRandomQuiz()
    }

    fun refreshRandomQuiz() {
        updateRandomQuiz()
    }

    private suspend fun fetchQuiz(): List<Question> = QuizAPI().getQuiz().questions
    private suspend fun fetchRandomQuiz(): List<Question> = QuizAPI().getRandomQuiz().questions

    private fun updateQuiz(){
        coroutineScope.launch {
            _questionState.update { fetchQuiz() }
        }
    }

    private fun updateRandomQuiz(){
        coroutineScope.launch {
            _questionState.update { fetchRandomQuiz() }
        }
    }
}