import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import network.Answer

@Composable
fun AnswersList(answers: List<Answer>, selectedAnswer: Int, onAnswerSelected: (Int) -> Unit) {

    // Save interaction source to remove ripple effect
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        answers.forEach { answer ->
            Row(Modifier.clickableWithoutRipple(interactionSource = interactionSource, onClick = { onAnswerSelected(answer.id) }), verticalAlignment = Alignment.CenterVertically){
                RadioButton(
                    selected = ( selectedAnswer == answer.id ),
                    onClick = { onAnswerSelected(answer.id) },
                    modifier = Modifier.padding(horizontal = 5.dp),
                    colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF3D00), unselectedColor = Color.White)
                )
                Text(answer.label, color = Color.White)
            }
        }
    }
}

// Create custom modifier to remove ripple effect
fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) = composed(
    factory = {
        this.then(
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() }
            )
        )
    }
)