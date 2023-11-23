import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberImagePainter

fun main() = application {
    Window(title="PokéQuiz", onCloseRequest = ::exitApplication) {
        mainView()
    }
}



