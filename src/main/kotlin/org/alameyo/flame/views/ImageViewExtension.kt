package org.alameyo.flame.views

import javafx.scene.control.Button
import javafx.scene.image.ImageView

fun ImageView.fitSize(button: Button) {
    this.fitHeightProperty().bind(button.heightProperty())
    this.fitWidthProperty().bind(button.widthProperty())}