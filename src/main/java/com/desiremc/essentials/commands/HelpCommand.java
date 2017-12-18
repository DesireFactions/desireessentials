package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;

import java.util.List;

public class HelpCommand extends ValidCommand
{

    public HelpCommand()
    {
        super("help", "View all help message.", Rank.GUEST, new String[] {"?"});
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        for (String str : DesireEssentials.getLangHandler().getStringList("help"))
        {
            sender.sendMessage(str);
        }
    }

}
