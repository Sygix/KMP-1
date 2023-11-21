
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScoreScreen(navigator: Navigator, score: String) {
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
                Text("Score", fontSize = 24.sp, modifier = Modifier.padding(vertical = 5.dp))
                Text("You got $score.", modifier = Modifier.padding(vertical = 5.dp))
                Button(
                    onClick = { navigator.navigate("/quiz") },
                    modifier = Modifier.padding(vertical = 5.dp),
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF3D00)),
                    elevation = ButtonDefaults.elevation(0.dp)
                ){
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Restart quiz icon")
                    Text("Restart", modifier = Modifier.padding(start = 5.dp))
                }
            }
        }
    }
}