package org.alameyo.flame.views

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings
import tornadofx.*

class SettingsView : View("My View") {

    private val flameController: FlameController by inject()
    private val flameConnectionConfigurationSettings: FlameConnectionConfigurationSettings by inject()
    private val timeout = SimpleStringProperty()
    private val port = SimpleStringProperty()
    private val resource = SimpleStringProperty()

    init {
        flameConnectionConfigurationSettings.loadProperties()
        port.value = flameConnectionConfigurationSettings.readPort()
        timeout.value = flameConnectionConfigurationSettings.readTimeout()
        resource.value = flameConnectionConfigurationSettings.readResource()
    }

    override val root = vbox {
        tabpane {
            tab("Login Settings") {
                form {
                    fieldset {
                        field("Port") {
                            textfield(port)
                        }
                        field("Timeout") {
                            textfield(timeout)
                        }
                        field("Resource") {
                            textfield(resource)
                        }
                    }
                }
            }

            tab("Security") {

            }

            tab("Proxy") {

            }

            tab("SSO") {

            }
        }
        button("Go back") {
            action {
                runAsync {
                    saveSettings()
                }
                replaceWith<MainView>()
            }
        }
    }

    private fun saveSettings() {
        flameConnectionConfigurationSettings.writePort(port.value)
        flameConnectionConfigurationSettings.writeResource(resource.value)
        flameConnectionConfigurationSettings.writeTimeout(timeout.value)
        flameConnectionConfigurationSettings.saveProperties()
    }
}
