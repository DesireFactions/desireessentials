package com.desiremc.essentials.validators;

import com.desiremc.core.api.newcommands.Validator;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;

public class HasEnoughMoneyValidator implements Validator<Double>
{

    @Override
    public boolean validateArgument(Session sender, String[] label, Double arg)
    {
        HCFSession session = HCFSessionHandler.getHCFSession(sender.getUniqueId());
        if (session.getBalance() < arg)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "economy.too_poor", "{amount}", arg.toString(), "{balance}", Double.toString(session.getBalance()));
            return false;
        }
        return true;
    }

}
