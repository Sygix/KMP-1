import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    PreComposeApp {
        MaterialTheme {
            rootNavHost()
        }
    }
}

expect fun getPlatformName(): String