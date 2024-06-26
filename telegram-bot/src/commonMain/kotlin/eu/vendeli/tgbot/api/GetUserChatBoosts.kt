@file:Suppress("MatchingDeclarationName")

package eu.vendeli.tgbot.api

import eu.vendeli.tgbot.interfaces.Action
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.boost.UserChatBoosts
import eu.vendeli.tgbot.types.internal.TgMethod
import eu.vendeli.tgbot.utils.getReturnType
import eu.vendeli.tgbot.utils.toJsonElement

class GetUserChatBoostsAction(
    val userId: Long,
) : Action<UserChatBoosts>() {
    override val method = TgMethod("getUserChatBoosts")
    override val returnType = getReturnType()

    init {
        parameters["user_id"] = userId.toJsonElement()
    }
}

/**
 * Use this method to get the list of boosts added to a chat by a user. Requires administrator rights in the chat. Returns a UserChatBoosts object.
 *
 * [Api reference](https://core.telegram.org/bots/api#getuserchatboosts)
 * @param chatId Unique identifier for the chat or username of the channel (in the format @channelusername)
 * @param userId Unique identifier of the target user
 * @returns [UserChatBoosts]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun getUserChatBoosts(userId: Long) = GetUserChatBoostsAction(userId)

@Suppress("NOTHING_TO_INLINE")
inline fun getUserChatBoosts(user: User) = getUserChatBoosts(user.id)
