package me.salt.entities.cmd

import org.jetbrains.spek.api.Spek

class CommandListenerTest : Spek({
    var listener = CommandListener()

    beforeEachTest { listener = CommandListener() }
})