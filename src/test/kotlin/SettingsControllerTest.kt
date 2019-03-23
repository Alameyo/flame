import javafx.stage.Stage
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings
import org.junit.jupiter.api.Test
import org.testfx.framework.junit5.ApplicationTest
import tornadofx.Scope
import tornadofx.find

class SettingsControllerTest : ApplicationTest() {

    private lateinit var testScope: Scope
    private lateinit var settings: FlameConnectionConfigurationSettings

    private val port = "2555"
    private val port2 = "5222"
    private val resource = "emalf"
    private val resource2 = "flame"
    private val timeout = "4"
    private val timeout2 = "10"


    override fun start(stage: Stage?) {
        super.start(stage)
        testScope = Scope()
        settings = find(testScope)

        settings.writeResource(resource)
        settings.writePort(port)
        settings.writeTimeout(timeout)
        settings.saveProperties()
    }

    @Test
    fun `Assert that settings are changed()`() {
        settings.loadProperties()
        assert(settings.readResource() == resource)
        assert(settings.readPort() == port)
        assert(settings.readTimeout() == timeout)

        settings.writeResource(resource2)
        settings.writePort(port2)
        settings.writeTimeout(timeout2)
        settings.saveProperties()

        settings.loadProperties()
        assert(settings.readResource() == resource2)
        assert(settings.readPort() == port2)
        assert(settings.readTimeout() == timeout2)
    }
}
