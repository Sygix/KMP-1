
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navigator: Navigator) {
    Column(
        Modifier.fillMaxSize().background(Color(0xFF1E1E1E)),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
                Text("Quiz", fontSize = 24.sp, modifier = Modifier.padding(vertical = 5.dp))
                Text("A simple Pokémon Quiz", modifier = Modifier.padding(vertical = 5.dp))
                Button(
                    onClick = { navigator.navigate("/quiz/1") },
                    modifier = Modifier.padding(vertical = 5.dp),
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF3D00)),
                    elevation = ButtonDefaults.elevation(0.dp)
                ){
                    Text("Start the Quiz")
                }
                Button(
                    onClick = {
                        runBlocking {
                            launch {
                                repo.updateRandomQuiz()
                                // Attendez jusqu'à ce que la valeur soit disponible ou après un certain délai
                                while (repo.randomQuizState.value.isEmpty()) {
                                    delay(100) // Attendez 100 millisecondes avant de vérifier à nouveau
                                }
                                // Une fois que la valeur est disponible, naviguez vers la page "quiz/random"
                                navigator.navigate("/quiz/random")
                            }
                        }
                    },
                    modifier = Modifier.padding(vertical = 5.dp),
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF3D00)),
                    elevation = ButtonDefaults.elevation(0.dp)
                ){
                    Text("Start a Random Quiz")
                }
            }
        }
    }
}