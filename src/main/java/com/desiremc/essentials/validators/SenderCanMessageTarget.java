package com.desiremc.essentials.validators;

import com.desiremc.core.api.newcommands.Validator;
import com.desiremc.core.session.Session;
import com.desiremc.core.session.SessionSetting;
import com.desiremc.essentials.DesireEssentials;

public class SenderCanMessageTarget implements Validator<Session>
{

    @Override
    public boolean validateArgument(Session sender, String[] label, Session arg)
    {
        if (!arg.getSetting(SessionSetting.MESSAGES) && !sender.getRank().isStaff())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "message.disabled", true, false);
            return false;
        }
        return true;
    }
}
