package org.alameyo.flame.views.login

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings
import org.alameyo.flame.css.FlameStyle
import tornadofx.*

class SettingsView : View("My View") {

    private val flameConnectionConfigurationSettings: FlameConnectionConfigurationSettings by inject()
    private val timeout = SimpleStringProperty()
    private val port = SimpleStringProperty()
    private val resource = SimpleStringProperty()

    init {
        loadSettings()
    }

    override val root = vbox {
        addClass(FlameStyle.settings)
        tabpane {
            tab("Login Settings") {
                form {
                    addClass(FlameStyle.settingsForm)
                    fieldset {
                        field("Port") {
                            textfield(port){
                                addClass(FlameStyle.settingsField)
                            }
                        }
                        field("Timeout") {
                            textfield(timeout) {
                                addClass(FlameStyle.settingsField)
                            }
                        }
                        field("Resource") {
                            textfield(resource) {
                                addClass(FlameStyle.settingsField)
                            }
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

    private fun loadSettings() {
        flameConnectionConfigurationSettings.loadProperties()
        port.value = flameConnectionConfigurationSettings.readPort()
        timeout.value = flameConnectionConfigurationSettings.readTimeout()
        resource.value = flameConnectionConfigurationSettings.readResource()
    }
}
