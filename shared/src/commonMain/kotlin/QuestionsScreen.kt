import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import network.data.Quiz

@Composable
fun QuestionScreen(quiz: Quiz) {

    var questionProgress by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(1) }
    var score by remember { mutableStateOf(0) }

    val onAnswerSelected: (Int) -> Unit = { answerId ->
        selectedAnswer = answerId
    }

    val submitAnswer: () -> Unit = {
        if(quiz.questions.size - 1 >= questionProgress) {
            //return ici
        }
        if (quiz.questions[questionProgress].correctAnswerId == selectedAnswer) {
            score++
        }
        questionProgress++
        selectedAnswer = 1
    }

    Column(
        Modifier.fillMaxSize().background(Color(0xFF1E1E1E)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(2.dp, Color.White),
            backgroundColor = Color(0xFF1E1E1E),
            contentColor = Color.White,
            elevation = 15.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Question : ", fontSize = 24.sp, modifier = Modifier.padding(vertical = 5.dp))
                Text(quiz.questions[questionProgress].label, modifier = Modifier.padding(vertical = 5.dp))
            }
        }

        AnswersScreen(quiz.questions[questionProgress].answers, selectedAnswer, onAnswerSelected)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = submitAnswer,
                modifier = Modifier.padding(vertical = 5.dp),
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF3D00), contentColor = Color.White),
                elevation = ButtonDefaults.elevation(0.dp)
            ){
                if(questionProgress >= quiz.questions.size - 1) {
                    Text("Finish", modifier = Modifier.padding(vertical = 5.dp))
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Finish icon")
                } else {
                    Text("Next", modifier = Modifier.padding(vertical = 5.dp))
                    Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Next icon")
                }
            }
            LinearProgressIndicator(
                progress = (questionProgress) / quiz.questions.size.toFloat(),
                modifier = Modifier.padding(20.dp),
                color = Color(0xFFFF3D00),
                strokeCap = StrokeCap.Round,
            )
        }
    }
}