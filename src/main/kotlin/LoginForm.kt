import javafx.scene.layout.BorderPane
import tornadofx.*

fun main() {
    launch<Login>()
}

class Login: App(LoginForm::class)

class LoginForm: View() {
    override val root : BorderPane by fxml()
}