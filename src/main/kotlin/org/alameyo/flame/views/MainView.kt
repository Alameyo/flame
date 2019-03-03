package org.alameyo.flame.views

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.FlameController
import tornadofx.*

class MainView : View() {

    private val controller: FlameController by inject()
    private val usernameInput = SimpleStringProperty()
    private val domainInput = SimpleStringProperty()
    private val passwordInput = SimpleStringProperty()

    private val fieldPadding = 10.0
    private val maxFieldWidth = 800.0

    override val root = vbox {
        borderpane {
            center = form {
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
                            runAsync {
                                controller.connect(
                                    usernameInput.value,
                                    domainInput.value,
                                    passwordInput.value
                                )
                            } ui {
                                if (it) replaceWith<FlameApplicationView>()
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
}
