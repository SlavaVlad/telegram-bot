@file:Suppress("MatchingDeclarationName")

package eu.vendeli.tgbot.api.chat

import eu.vendeli.tgbot.interfaces.Action
import eu.vendeli.tgbot.interfaces.ActionState
import eu.vendeli.tgbot.types.internal.TgMethod
import eu.vendeli.tgbot.utils.getReturnType

class UnpinChatMessageAction(messageId: Long) : Action<Boolean>, ActionState() {
    override val method: TgMethod = TgMethod("unpinChatMessage")
    override val returnType = getReturnType()

    init {
        parameters["message_id"] = messageId
    }
}

fun unpinChatMessage(messageId: Long) = UnpinChatMessageAction(messageId)
