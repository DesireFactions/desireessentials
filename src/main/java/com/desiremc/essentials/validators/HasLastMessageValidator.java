package com.desiremc.essentials.validators;

import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.CommandValidator;
import com.desiremc.core.utils.PlayerUtils;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.commands.MessageCommand;

public class HasLastMessageValidator extends CommandValidator
{

    @Override
    public boolean validateArgument(CommandSender sender, String label, Object arg)
    {
        if (!MessageCommand.history.containsKey(PlayerUtils.getUUIDFromSender(sender)))
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "message.no_last");
            return false;
        }
        return true;
    }

}
