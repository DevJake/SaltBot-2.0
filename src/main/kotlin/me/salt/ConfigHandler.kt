package me.salt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import java.io.File

object ConfigHandler {
    fun writeValue(path: File, value: Any) {
        GenUtil.createFileFromResources(path)
        val mapper = ObjectMapper(YAMLFactory()
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
                .enable(YAMLGenerator.Feature.SPLIT_LINES)
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
        mapper.writeValue(File(File(GenUtil.saltResourceDir, path.parentFile.path), path.name), value)
    }

    fun writeValue(path: String, value: Any) = writeValue(File(path), value)

    public val saltConfigHandler = SaltConfigHandler()
    public val guildConfigHandler = GuildConfigHandler()
    public val textChannelConfigHandler = TextChannelConfigHandler()
    public val userConfigHandler = UserConfigHandler()
}

class SaltConfigHandler : GenericConfigHandler {

    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class GuildConfigHandler : GenericConfigHandler {
    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class TextChannelConfigHandler : GenericConfigHandler {
    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class UserConfigHandler : GenericConfigHandler {
    override fun getMainConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoggingConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStatsConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLangConfig(entityId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private interface GenericConfigHandler {
    fun getMainConfig(entityId: String?)
    fun getPermConfig(entityId: String?)
    fun getLoggingConfig(entityId: String?)
    fun getStatsConfig(entityId: String?)
    fun getLangConfig(entityId: String?)

    fun getMainConfig(entityId: Long?) = getMainConfig(entityId.toString())
    fun getPermConfig(entityId: Long?) = getPermConfig(entityId.toString())
    fun getLoggingConfig(entityId: Long?) = getLoggingConfig(entityId.toString())
    fun getStatsConfig(entityId: Long?) = getStatsConfig(entityId.toString())
    fun getLangConfig(entityId: Long?) = getLangConfig(entityId.toString())

    enum class Entity {
        GUILD,
        TEXTCHANNEL,
        USER;
    }
}

private interface SectionHandler {
    fun getSampleSaltConfig()
    fun getSampleGuildConfig()
    fun getSampleTextChannelConfig()
    fun getSampleUserConfig()
}

class EntityConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class PermissionConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class LogConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class StatsConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class LanguageConfig : SectionHandler {
    override fun getSampleSaltConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleGuildConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleTextChannelConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSampleUserConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}