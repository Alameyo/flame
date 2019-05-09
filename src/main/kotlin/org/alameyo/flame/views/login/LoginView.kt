package org.alameyo.flame.views.login

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings
import org.alameyo.flame.css.FlameStyle
import org.alameyo.flame.views.home.FlameApplicationView
import tornadofx.*
import java.lang.IllegalArgumentException

class LoginView : View() {

    private val controller: FlameController by inject()
    private val flameConnectionConfigurationSettings: FlameConnectionConfigurationSettings by inject()

    private val usernameInput = SimpleStringProperty()
    private val passwordInput = SimpleStringProperty()

    private val fieldPadding = 10.0
    private val maxFieldWidth = 400.0

    init {
        loadSettings()
    }

    override val root = vbox {
        addClass(FlameStyle.loginBackground)
        borderpane {
            center = form {
                addClass(FlameStyle.loginBox)
                fieldset {
                    field("Username") {
                        textfield(usernameInput)
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
                            runAsync {
                                val (username, domain) = splitJid(usernameInput)
                                controller.connect(
                                        username,
                                        domain,
                                        passwordInput.value
                                )
                            } ui {
                                if (it) {
                                    replaceWith<FlameApplicationView>()
                                    saveSettings()
                                }
                            }
                        }
                    }

                    button("Settings") {
                        action {
                            replaceWith<SettingsView>()
                        }
                    }
                }
            }
        }
    }

    private fun splitJid(usernameInput: SimpleStringProperty): List<String> {
        val usernameInputList = usernameInput.value.split("@")
        validateJid(usernameInputList)
        return usernameInputList
    }

    private fun validateJid(usernameInputList: List<String>) {
        when {
            usernameInputList.size != 2 -> throw IllegalArgumentException("Jid not in valid form, specify Jid as username@domain")
            !usernameInputList[1].contains(".") ->
                throw IllegalArgumentException("Jid not in valid form, domain should have top level domain and local part")
        }
    }

    private fun saveSettings() {
        flameConnectionConfigurationSettings.writeUserJid(usernameInput.value)
        flameConnectionConfigurationSettings.saveProperties()
    }

    private fun loadSettings() {
        flameConnectionConfigurationSettings.loadProperties()
        usernameInput.value = flameConnectionConfigurationSettings.readUserJid()
    }
}
