@file:Suppress("MatchingDeclarationName")

package eu.vendeli.tgbot.api.forum

import eu.vendeli.tgbot.interfaces.SimpleAction
import eu.vendeli.tgbot.types.internal.TgMethod

/**
 * Use this method to hide 'General' topic in a forum supergroup chat.
 * The bot must be an administrator in the chat for this to work
 * and must have the can_manage_topics administrator rights.
 * Returns True on success.
 */
class HideGeneralForumTopicAction : SimpleAction<Boolean> {
    override val method: TgMethod = TgMethod("hideGeneralForumTopic")
    override val parameters: MutableMap<String, Any?> = mutableMapOf()
}

/**
 * Use this method to hide 'General' topic in a forum supergroup chat.
 * The bot must be an administrator in the chat for this to work
 * and must have the can_manage_topics administrator rights.
 * Returns True on success.
 */
fun hideGeneralForumTopic() = HideGeneralForumTopicAction()