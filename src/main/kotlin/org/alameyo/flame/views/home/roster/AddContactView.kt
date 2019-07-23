package org.alameyo.flame.views.home.roster

import javafx.beans.property.SimpleStringProperty
import org.alameyo.flame.controllers.chat.RosterController
import org.alameyo.flame.css.FlameStyle.Companion.littleRoundButton
import org.alameyo.flame.views.fitSize
import org.jxmpp.jid.impl.JidCreate.bareFrom
import tornadofx.*

class AddContactView : View() {

    private val rosterController: RosterController by inject()
    private val rosterView = find<RosterView>()

    private val jid = SimpleStringProperty()
    private val name = SimpleStringProperty()

    override val root = scrollpane {
        hbox {
            form {
                fieldset {
                    field("Contact JID") {
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
                                    rosterController.addContactToRoster(bareFrom(jid.value), name.value)
                                } ui {
                                    rosterView.populateScrollPane(rosterView.holdBox)
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