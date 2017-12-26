package com.desiremc.essentials.validators;

import com.desiremc.core.api.newcommands.Validator;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.session.FSession;
import com.desiremc.hcf.session.FSessionHandler;

public class HasEnoughMoneyValidator implements Validator<Double>
{

    @Override
    public boolean validateArgument(Session sender, String[] label, Double arg)
    {
        FSession session = FSessionHandler.getGeneralFSession(sender.getUniqueId());
        if (session.getBalance() < arg && !session.getSession().isConsole())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "economy.too_poor", true, false, "{amount}", arg.toString(), "{balance}", Double.toString(session.getBalance()));
            return false;
        }
        return true;
    }

}
