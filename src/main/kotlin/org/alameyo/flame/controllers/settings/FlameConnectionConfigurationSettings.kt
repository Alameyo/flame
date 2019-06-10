package org.alameyo.flame.controllers.settings

import com.sun.javafx.PlatformUtil
import com.sun.javafx.PlatformUtil.isLinux
import com.sun.javafx.PlatformUtil.isWindows
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings.Path.LOGIN_SETTINGS
import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings.PropertyName.*
import tornadofx.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.set

class FlameConnectionConfigurationSettings : Controller() {

    private val flameProperties = Properties()

    fun writeUserJid(userJid: String) {
        flameProperties[USER_JID.propertyName] = userJid
    }

    fun readUserJid() = flameProperties.getProperty(USER_JID.propertyName) ?: USER_JID.defaultValue

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

    fun checkIfPropertiesExist(): Boolean {
        val file = File(LOGIN_SETTINGS.path)
        return (file.exists() && !file.isDirectory)
    }

    fun loadProperties() = FileInputStream(LOGIN_SETTINGS.path).use { flameProperties.load(it) }

    fun saveProperties() = FileOutputStream(LOGIN_SETTINGS.path).use { flameProperties.store(it, null) }

    private enum class PropertyName(val propertyName: String, val defaultValue: String = "") {
        USER_JID("user_jid"),
        TIMEOUT("timeout", "10000"),
        RESOURCE("resource", "flame"),
        PORT("port", "5222")
    }

    private enum class Path(val path: String) {
        APPDATA(if (isLinux()) "" else System.getenv("APPDATA")),
        USER_HOME(if (isWindows()) APPDATA.path else System.getProperty("user.home")),
        FLAME_HOME("${USER_HOME.path}${File.separator}flame"),
        LOGIN_SETTINGS(FLAME_HOME.path + File.separator + "login_settings.properties")
    }

    fun createProperties() {
        val file = File(LOGIN_SETTINGS.path)
        file.parentFile.mkdirs()
        file.createNewFile()
    }

}
