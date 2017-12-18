package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.newparsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PlayerChangeCommand extends ValidCommand
{

    public PlayerChangeCommand(String name, String description, Rank requiredRank, String[] args, String... aliases)
    {
        super(name, description, requiredRank, true, aliases);

        addArgument(CommandArgumentBuilder.createBuilder(Player.class)
                .setName("target")
                .setOptional()
                .setParser(new PlayerParser())
                .build());
    }

    @Override
    public void validRun(Session sender, String label[], List<CommandArgument<?>> args)
    {
        Player p;
        if (!args.get(args.size() - 1).hasValue())
        {
            p = (Player) sender;
        }
        else
        {
            p = (Player) args.get(args.size() - 1).getValue();
        }
        ArrayList<Object> renders = new ArrayList<>(Arrays.asList(applyChanges(p, Arrays.copyOfRange(args, 0, this.args.length - 1))));
        renders.add("{target}");
        renders.add(p.getName());
        if (p != sender)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others", renders);
        }
        DesireEssentials.getLangHandler().sendRenderMessage(p, name.toLowerCase() + ".self", renders);
    }

    public abstract Object[] applyChanges(Player p, Object[] args);

}
