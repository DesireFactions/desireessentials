package com.desiremc.essentials.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;

public class FeedCommand extends ValidCommand
{

    public FeedCommand()
    {
        super("feed", "Fill your hunger bar.", Rank.ADMIN, ARITY_OPTIONAL, new String[] { "target" }, new String[] { "eat" });

        addParser(new PlayerParser(), "target");

        addOptionalNonexistantValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player p;
        if (args.length == 0)
        {
            p = (Player) sender;
        }
        else
        {
            p = (Player) args[0];
        }
        p.setFoodLevel(20);
        p.setSaturation(10);
        p.setExhaustion(0);
        if (p == sender)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "feed.self");
        }
        else
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "feed.others",
                    "{target}", p.getName());
        }
    }

}
