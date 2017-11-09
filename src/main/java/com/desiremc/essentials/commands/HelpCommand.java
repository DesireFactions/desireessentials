package com.desiremc.essentials.commands;

import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.essentials.DesireEssentials;

public class HelpCommand extends ValidCommand
{

    public HelpCommand()
    {
        super("help", "View all help message.", Rank.GUEST, new String[] {}, new String[] { "?" });
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        for (String str : DesireEssentials.getLangHandler().getStringList("help"))
        {
            sender.sendMessage(str);
        }
    }

}
