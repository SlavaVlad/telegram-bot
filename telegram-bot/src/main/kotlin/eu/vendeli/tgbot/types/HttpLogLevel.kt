package eu.vendeli.tgbot.types

import io.ktor.client.plugins.logging.*

/**
 * Proxy enum class for Ktor log leveling.
 *
 */
enum class HttpLogLevel {
    ALL,
    HEADERS,
    BODY,
    INFO,
    NONE;

    internal fun toKtorLvl() = when(this) {
        ALL -> LogLevel.ALL
        HEADERS -> LogLevel.HEADERS
        BODY -> LogLevel.BODY
        INFO -> LogLevel.INFO
        NONE -> LogLevel.NONE
    }
}