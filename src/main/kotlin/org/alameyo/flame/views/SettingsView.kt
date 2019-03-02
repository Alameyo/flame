package org.alameyo.flame.views

import tornadofx.*

class SettingsView : View("My View") {
    override val root = vbox {
        tabpane {
            tab("Login Settings") {

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
                replaceWith<MainView>()
            }
        }
    }
}
