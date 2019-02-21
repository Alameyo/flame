import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import tornadofx.*

fun main() {
    launch<Main>()
}

class Main : App(MainView::class)

class MainView : View() {
    private val controller: FlameController by inject()
    private val usernameInput = SimpleStringProperty()
    private val domainInput = SimpleStringProperty()
    private val passwordInput = SimpleStringProperty()

    private val fieldPadding = 10.0
    private val maxFieldWidth = 800.0

    private val form = form {
        fieldset {
            field("Username") {
                textfield(usernameInput)
                paddingAll = fieldPadding
                maxWidth = maxFieldWidth
            }

            field("Domain") {
                textfield(domainInput)
                paddingAll = fieldPadding
                maxWidth = maxFieldWidth
            }

            field("Password") {
                passwordfield(passwordInput)
                paddingAll = fieldPadding
                maxWidth = maxFieldWidth
            }

            button("Connect") {
                action {
                    controller.loadConnectionConfigurations(usernameInput.value, domainInput.value, passwordInput.value)
                }
            }
        }
    }
    val topView: TopViewHeader by inject()

    override val root = borderpane {
        top = topView.root
        center = form
    }
}

internal class FlameController : Controller() {
    lateinit var connectionConfiguration: XMPPTCPConnectionConfiguration
    lateinit var connection: XMPPTCPConnection

    fun loadConnectionConfigurations(usernameInput: String?, domainInput: String?, passwordInput: String?) {
        connectionConfiguration = XMPPTCPConnectionConfiguration.builder()
            .setCompressionEnabled(false)
            .setConnectTimeout(10_000)
            .setPort(5222)
            .setXmppDomain(domainInput)
            .setUsernameAndPassword(usernameInput, passwordInput)
            .build()
        println("Connecting to the server $usernameInput@$domainInput")
    }

    fun connect() {
        runAsync {
            connection = XMPPTCPConnection(connectionConfiguration)
        } ui {
            loadingIcon()
        }
    }

    fun loadingIcon(){

    }
}