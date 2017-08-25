package me.salt

import me.salt.config.initConfigs
import net.dv8tion.jda.core.JDA

class Main {

    companion object {
        lateinit var jda: JDA
            private set

        @JvmStatic fun main(args: Array<String>) {
            initConfigs() //Calls init method for configs
//            jda = JDABuilder(AccountType.BOT).setToken("").buildAsync()
            //TODO accept runtime params, such as regen-default-configs to regenerate default config files
        }
    }
}
