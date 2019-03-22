import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.views.login.MainView
import tornadofx.App
import tornadofx.launch

fun main() {
    launch<Main>()
}

class Main : App(MainView::class, FlameStyle::class)
