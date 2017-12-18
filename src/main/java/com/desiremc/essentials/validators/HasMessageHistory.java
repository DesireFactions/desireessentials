package com.desiremc.essentials.validators;

import com.desiremc.core.api.newcommands.SenderValidator;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.commands.MessageCommand;

public class HasMessageHistory implements SenderValidator
{

    @Override
    public boolean validate(Session sender)
    {
        if (!MessageCommand.history.containsKey(sender.getUniqueId()))
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "respond.no_one");
            return false;
        }
        return true;
    }

}
