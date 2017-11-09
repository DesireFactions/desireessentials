package com.desiremc.essentials.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.utils.StringUtils;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;

public abstract class PlayerChangeCommand extends ValidCommand
{

    public PlayerChangeCommand(String name, String description, Rank requiredRank, String[] args, String... aliases)
    {
        super(name, description, requiredRank, ARITY_OPTIONAL, StringUtils.add(args, "target"), aliases);

        addParser(new PlayerParser(), "target");

        addOptionalNonexistantValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player p;
        if (args.length == this.args.length - 1)
        {
            p = (Player) sender;
        }
        else
        {
            p = (Player) args[args.length - 1];
        }
        Object[] renders = applyChanges(p, Arrays.copyOfRange(args, 0, this.args.length - 1));
        if (p == sender)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".self",
                    renders);
        }
        else
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others",
                    "{target}", p.getName(),
                    renders);
        }
    }

    public abstract Object[] applyChanges(Player p, Object[] args);

}
