package org.alameyo.flame.css

import javafx.geometry.Pos
import tornadofx.*

class FlameStyle : Stylesheet() {

    companion object {
        val loginBackground by cssclass()
        val loginBox by cssclass()
        val settings by cssclass()
        val settingsField by cssclass()
        val settingsForm by cssclass()
        val settingsTab by cssclass()
        val rosterList by cssclass()
        val roundButton by cssclass()
    }

    init {
        loginBackground {
            padding = box(200.px)
            backgroundColor += c("#27324E")
            prefHeight = 600.px
            prefWidth = 800.px
        }

        loginBox {
            fontSize = 16.px
            backgroundColor += c("#EB5401")
        }

        settings {
            backgroundColor += c("#27324E")
        }

        settingsTab {
            padding = box(100.px)
        }

        settingsForm {
            backgroundColor += c("#EB5401")
        }

        settingsField {
            fontSize = 16.px
            prefHeight = 50.px
            prefWidth = 200.px
            maxHeight = 50.px
            maxWidth = 300.px
        }

        rosterList {
            padding = box(10.px)
        }

        roundButton {
            backgroundRadius = multi(box(50.px), box(50.px))
            minWidth = 80.px
            minHeight = 80.px
            maxWidth = 80.px
            maxHeight = 80.px
            alignment = Pos.CENTER
        }
    }
}
