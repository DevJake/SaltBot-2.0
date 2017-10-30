package me.salt.util.rest

import me.salt.entities.permissions.UserPermission
import java.time.OffsetDateTime

class RestController {
    private val tokens = mutableListOf<AccessToken>()

    fun genToken(oldToken: AccessToken): String {
        TODO()
    }

    fun tokenIsValid(token: String) { }
    fun tokenIsValid(token: AccessToken) = tokenIsValid(token.token)
}

data class AccessToken(
        val token: String,
        val active: Boolean,
        val tiedUserId: String,
        val genDateTime: OffsetDateTime,
        val accessPerms: List<UserPermission>
)
