package org.alameyo.flame.controllers.settings

import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings.Path.*
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings.PropertyName.*
import tornadofx.Controller
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class FlameConnectionConfigurationSettings : Controller() {

    private val flameProperties = Properties()

    fun writePort(port: String) {
        flameProperties[PORT.propertyName] = port
    }

    fun readPort() = flameProperties.getProperty(PORT.propertyName) ?: PORT.defaultValue

    fun writeResource(resource: String) {
        flameProperties[RESOURCE.propertyName] = resource
    }

    fun readResource() = flameProperties.getProperty(RESOURCE.propertyName) ?: RESOURCE.defaultValue

    fun writeTimeout(timeout: String) {
        flameProperties[TIMEOUT.propertyName] = timeout
    }

    fun readTimeout() = flameProperties.getProperty(TIMEOUT.propertyName) ?: TIMEOUT.defaultValue

    fun loadProperties() = FileInputStream(LOGIN_SETTINGS.path).use { flameProperties.load(it) }

    fun saveProperties() = FileOutputStream(LOGIN_SETTINGS.path).use { flameProperties.store(it, null) }

    private enum class PropertyName(val propertyName: String, val defaultValue: String) {
        TIMEOUT("timeout", "10000"),
        RESOURCE("resource", "flame"),
        PORT("port", "5222")
    }

    private enum class Path(val path: String) {
        USER_HOME(System.getProperty("user.home")),
        FLAME_HOME("${USER_HOME.path}${File.separator}flame"),
        LOGIN_SETTINGS(FLAME_HOME.path + File.separator + "login_settings.properties")
    }
}
