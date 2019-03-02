package org.alameyo.flame.controllers

import org.jivesoftware.smack.AbstractXMPPConnection
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode.*
import org.jivesoftware.smack.ReconnectionManager
import org.jivesoftware.smack.roster.Roster.getInstanceFor
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smack.util.DNSUtil.resolveXMPPServiceDomain
import org.minidns.dnsname.DnsName
import org.minidns.dnsname.DnsName.from
import tornadofx.Controller

class FlameController : Controller() {
    lateinit var connectionConfiguration: XMPPTCPConnectionConfiguration
    lateinit var connection: AbstractXMPPConnection
    lateinit var smackReconectionManager: ReconnectionManager

    private fun loadConnectionConfigurations(usernameInput: String?, domainInput: String?, passwordInput: String?) {
        connectionConfiguration = XMPPTCPConnectionConfiguration.builder()
            .setResource("flame0")
            .setCompressionEnabled(false)
            .setConnectTimeout(20_000)
            .setPort(5222)
            .setSecurityMode(ifpossible)
            .setUsernameAndPassword(usernameInput, passwordInput)
            .setHost(resolveHostFromDns(domainInput))
            .setXmppDomain(domainInput)
            .build()
    }

    fun connect(usernameInput: String?, domainInput: String?, passwordInput: String?) {
        loadConnectionConfigurations(usernameInput, domainInput, passwordInput)
        connection = XMPPTCPConnection(connectionConfiguration)
        connection.connect()
        println("Connecting to the server $usernameInput@$domainInput")
        connection.login()
        smackReconectionManager = ReconnectionManager.getInstanceFor(connection)
        test(connection)
    }

    private fun resolveHostFromDns(domainInput: String?): DnsName? =
        resolveXMPPServiceDomain(from(domainInput), null, ConnectionConfiguration.DnssecMode.disabled)[0].fqdn

    private fun test(connection: AbstractXMPPConnection) {
        val roster = getInstanceFor(connection)
        roster.reloadAndWait()
        val entries = roster.entries
        entries.forEach {
            println(it.name)
        }
    }
}