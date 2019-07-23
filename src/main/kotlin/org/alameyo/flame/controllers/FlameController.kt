package org.alameyo.flame.controllers

import org.alameyo.flame.controllers.settings.FlameConnectionConfigurationSettings
import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode.ifpossible
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smack.util.DNSUtil.resolveXMPPServiceDomain
import org.minidns.dnsname.DnsName
import org.minidns.dnsname.DnsName.from
import tornadofx.Controller

class FlameController : Controller() {

    lateinit var connection: AbstractXMPPConnection
    private val flameConnectionConfigurationSetting: FlameConnectionConfigurationSettings by inject()

    private lateinit var connectionConfiguration: XMPPTCPConnectionConfiguration

    private fun loadConnectionConfigurations(usernameInput: String?, domainInput: String?, passwordInput: String?) {
        connectionConfiguration = XMPPTCPConnectionConfiguration.builder()
            .setResource(flameConnectionConfigurationSetting.readResource())
            .setCompressionEnabled(false)
            .setConnectTimeout(flameConnectionConfigurationSetting.readTimeout().toInt())
            .setPort(flameConnectionConfigurationSetting.readPort().toInt())
            .setSecurityMode(ifpossible)
            .setUsernameAndPassword(usernameInput, passwordInput)
            .setHost(resolveHostFromDns(domainInput))
            .setXmppDomain(domainInput)
            .build()
    }

    fun connect(usernameInput: String?, domainInput: String?, passwordInput: String?): Boolean {
        loadConnectionConfigurations(usernameInput, domainInput, passwordInput)
        connection = XMPPTCPConnection(connectionConfiguration)
        connection.connect()
        println("Connecting to the server $usernameInput@$domainInput")
        connection.login()
        return connection.isAuthenticated
    }

    private fun resolveHostFromDns(domainInput: String?): DnsName? =
        resolveXMPPServiceDomain(from(domainInput), null, ConnectionConfiguration.DnssecMode.disabled)[0].fqdn

}
