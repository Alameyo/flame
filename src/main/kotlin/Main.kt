import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.views.login.LoginView
import tornadofx.App
import tornadofx.launch

fun main() {
    launch<Main>()
}

class Main : App(LoginView::class, FlameStyle::class)
