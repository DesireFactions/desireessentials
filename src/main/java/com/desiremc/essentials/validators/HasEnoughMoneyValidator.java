package com.desiremc.essentials.validators;

import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.CommandValidator;
import com.desiremc.core.session.HCFSession;
import com.desiremc.core.session.HCFSessionHandler;
import com.desiremc.essentials.DesireEssentials;

public class HasEnoughMoneyValidator extends CommandValidator
{

    @Override
    public boolean validateArgument(CommandSender sender, String label, Object arg)
    {
        HCFSession session = HCFSessionHandler.getHCFSession(sender);
        if (session.getBalance() < (Double) arg)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "economy.too_poor", "{amount}", arg.toString(), "{balance}", Double.toString(session.getBalance()));
            return false;
        }
        return true;
    }

}
