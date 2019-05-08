package org.alameyo.flame.css

import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class FlameStyle : Stylesheet() {

    companion object {
        val loginBackground by cssclass()
        val loginBox by cssclass()
        val settings by cssclass()
        val settingsField by cssclass()
        val settingsForm by cssclass()
        val settingsTab by cssclass()
        val leftSideTab by cssclass()
        val scrollPaneStyle by cssclass()
        val rosterList by cssclass()
        val roundButton by cssclass()
        val chatVboxStyle by cssclass()
        val chatScrollPaneStyle by cssclass()
        val chatTextFieldStyle by cssclass()
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

        leftSideTab {
            backgroundColor += c("#27324E")
        }

        scrollPaneStyle {
            backgroundColor += Color.TRANSPARENT
            thumb {
                backgroundColor +=Color.BLACK
                baseColor += Color.BLACK
            }
            and(scrollBar) {
                backgroundColor += Color.TRANSPARENT
                and(vertical, track) {
                    backgroundColor += Color.BLACK

                    borderColor += box(Color.TRANSPARENT)
                    backgroundRadius = multi(box(0.em))
                }

                and(thumb) {
                    backgroundColor += Color.BLACK
                    baseColor = Color.BLACK
                }
            }
        }

        rosterList {
            padding = box(10.0.px, 20.0.px)
        }

        roundButton {
            backgroundRadius = multi(box(40.px), box(40.px))
            minWidth = 70.px
            minHeight = 70.px
            maxWidth = 70.px
            maxHeight = 70.px
            alignment = Pos.CENTER
        }

        chatVboxStyle {
            backgroundColor += c("#27324E")
            prefHeight = 500.px
            maxHeight = 500.px
            prefWidth = 600.px
        }

        chatScrollPaneStyle {
            baseColor = c("#27324E")
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
