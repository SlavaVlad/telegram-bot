package eu.vendeli.tgbot

import eu.vendeli.tgbot.core.CodegenUpdateHandler
import eu.vendeli.tgbot.core.FunctionalHandlingDsl
import eu.vendeli.tgbot.interfaces.ConfigLoader
import eu.vendeli.tgbot.types.internal.UpdateType
import eu.vendeli.tgbot.types.internal.configuration.BotConfiguration
import eu.vendeli.tgbot.types.media.File
import eu.vendeli.tgbot.utils.BotConfigurator
import eu.vendeli.tgbot.utils.FunctionalHandlingBlock
import eu.vendeli.tgbot.utils.Logging
import eu.vendeli.tgbot.utils.getConfiguredHttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes

/**
 * Telegram bot main instance
 *
 * @property token Token of your bot
 *
 * @param commandsPackage The place where the search for commands and inputs will be done.
 * @param botConfiguration Lambda function to customize the bots instance. See [BotConfiguration]
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
class TelegramBot(
    internal val token: String,
    commandsPackage: String? = null,
    botConfiguration: BotConfigurator = {},
) {
    /**
     * Constructor to build through configuration loader.
     */
    constructor(configLoader: ConfigLoader) : this(
        configLoader.token,
        configLoader.commandsPackage,
    ) {
        config.apply(configLoader.load())
    }

    internal val config = BotConfiguration().apply(botConfiguration)

    /**
     * A tool for managing input waiting.
     */
    val inputListener get() = config.inputListener

    /**
     * A tool for managing User context.
     */
    val userData get() = config.context.userData

    /**
     * A tool for managing Chat context.
     */
    val chatData get() = config.context.chatData

    /**
     * Property to identify different bot instances during multi-bot processing.
     */
    var identifier: String = "KtGram"

    init {
        logger.setLevel(config.logging.botLogLevel)
    }

    /**
     * Current bot UpdateHandler instance
     */
    val update = CodegenUpdateHandler(commandsPackage, this)

    internal val httpClient = getConfiguredHttpClient()

    /**
     * Get direct url from [File] if [File.filePath] is present
     *
     * @param file
     * @return direct url to file
     */
    fun getFileDirectUrl(file: File): String? = file.getDirectUrl(config.apiHost, token)

    /**
     * Get file from [File] if [File.filePath] is present.
     *
     * @param file
     * @return [ByteArray]
     */
    suspend fun getFileContent(file: File): ByteArray? =
        file.getDirectUrl(config.apiHost, token)?.let { httpClient.get(it).readBytes() }

    /**
     * Function for processing updates by long-pulling using annotation commands.
     *
     * Note that when using this method, other processing will be interrupted and reassigned.
     */
    suspend fun handleUpdates(allowedUpdates: List<UpdateType>? = null) {
        update.setListener(allowedUpdates) {
            handle(it)
        }
    }

    /**
     * Function for processing updates by long-pulling using functional handling.
     *
     * Note that when using this method, other processing will be interrupted and reassigned.
     *
     * @param block [FunctionalHandlingDsl]
     */
    suspend fun handleUpdates(allowedUpdates: List<UpdateType>? = null, block: FunctionalHandlingBlock) {
        update.setListener(allowedUpdates) {
            handle(it, block)
        }
    }

    internal companion object : Logging()
}
