package org.alameyo.flame.css

import javafx.geometry.Pos
import javafx.scene.paint.Color
import org.alameyo.flame.css.FlameColors.*
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
        val littleRoundButton by cssclass()
        val chatVboxStyle by cssclass()
        val chatScrollPaneStyle by cssclass()
        val chatTextFieldStyle by cssclass()
    }

    init {
        loginBackground {
            padding = box(200.px)
            backgroundColor += c(BACKGROUND.colorHex)
            prefHeight = 600.px
            prefWidth = 800.px
        }

        loginBox {
            fontSize = 16.px
            backgroundColor += c(BACKGROUND_ORANGE.colorHex)
        }

        settings {
            backgroundColor += c(BACKGROUND.colorHex)
        }

        settingsTab {
            padding = box(100.px)
        }

        settingsForm {
            backgroundColor += c(BACKGROUND_ORANGE.colorHex)
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

        littleRoundButton {
            backgroundRadius = multi(box(30.px), box(30.px))
            minWidth = 40.px
            minHeight = 40.px
            maxWidth = 40.px
            maxHeight = 40.px
            alignment = Pos.CENTER
        }

        chatVboxStyle {
            backgroundColor += c(BACKGROUND.colorHex)
            prefHeight = 500.px
            maxHeight = 500.px
            prefWidth = 600.px
        }

        chatScrollPaneStyle {
            baseColor = c(BACKGROUND.colorHex)
            backgroundColor += Color.TRANSPARENT
            prefHeight = 500.px
            maxHeight = 500.px
            prefWidth = 600.px
        }

        chatTextFieldStyle {
            prefWidth = 600.px
        }
    }
}
