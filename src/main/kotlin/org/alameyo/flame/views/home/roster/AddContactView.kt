package org.alameyo.flame.views.home.roster

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.FlameController
import org.alameyo.flame.css.FlameStyle.Companion.littleRoundButton
import org.alameyo.flame.views.fitSize
import org.jxmpp.jid.impl.JidCreate
import tornadofx.*

class AddContactView : View() {

    private val flameController: FlameController by inject()
    private val rosterController = flameController.rosterController
    private val rosterView = find<RosterView>()

    private val jid = SimpleStringProperty()
    private val name = SimpleStringProperty()

    override val root = scrollpane {
        hbox {
            form {
                fieldset {
                    field("JID") {
                        textfield(jid)
                    }
                    field("Name") {
                        textfield(name)
                    }
                    button {
                        addClass(littleRoundButton)
                        imageview("UI/Friend_Add.png").fitSize(this)
                        action {
                            if (validateInput()) {
                                runAsync {
                                    rosterController.addContactToRoster(JidCreate.bareFrom(jid.value), name.value)
                                } ui {
                                    rosterView.populateRoster(rosterView.holdBox)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateInput() = !jid.value.isNullOrEmpty()


}