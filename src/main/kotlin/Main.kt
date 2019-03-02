import org.alameyo.flame.views.MainView
import tornadofx.App
import tornadofx.launch

fun main() {
    launch<Main>()
}

class Main : App(MainView::class)
