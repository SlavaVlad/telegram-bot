@file:Suppress("MatchingDeclarationName")

package eu.vendeli.tgbot.api.botactions

import eu.vendeli.tgbot.interfaces.SimpleAction
import eu.vendeli.tgbot.types.chat.ChatAdministratorRights
import eu.vendeli.tgbot.types.internal.TgMethod
import eu.vendeli.tgbot.utils.encodeWith
import eu.vendeli.tgbot.utils.getReturnType
import eu.vendeli.tgbot.utils.toJsonElement

class SetMyDefaultAdministratorRightsAction(
    rights: ChatAdministratorRights? = null,
    forChannel: Boolean? = null,
) : SimpleAction<Boolean>() {
    override val method = TgMethod("setMyDefaultAdministratorRights")
    override val returnType = getReturnType()

    init {
        if (rights != null) parameters["rights"] = rights.encodeWith(ChatAdministratorRights.serializer())
        if (forChannel != null) parameters["for_channel"] = forChannel.toJsonElement()
    }
}

/**
 * Use this method to change the default administrator rights requested by the bot when it's added as an administrator to groups or channels. These rights will be suggested to users, but they are free to modify the list before adding the bot. Returns True on success.
 *
 * [Api reference](https://core.telegram.org/bots/api#setmydefaultadministratorrights)
 * @param rights A JSON-serialized object describing new default administrator rights. If not specified, the default administrator rights will be cleared.
 * @param forChannels Pass True to change the default administrator rights of the bot in channels. Otherwise, the default administrator rights of the bot for groups and supergroups will be changed.
 * @returns [Boolean]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun setMyDefaultAdministratorRights(rights: ChatAdministratorRights? = null, forChannel: Boolean? = null) =
    SetMyDefaultAdministratorRightsAction(rights, forChannel)
