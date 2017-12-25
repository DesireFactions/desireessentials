package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.entity.Player;

import java.util.List;

public class FlyCommand extends ValidCommand
{
    public FlyCommand()
    {
        super("fly", "Turn on/off fly mode.", Rank.ADMIN, new String[] {});

        addArgument(CommandArgumentBuilder.createBuilder(Player.class)
                .setName("target")
                .setParser(new PlayerParser())
                .setAllowsConsole()
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player player;

        if (arguments.get(0).hasValue())
        {
            player = (Player) arguments.get(0).getValue();
        }
        else
        {
            player = sender.getPlayer();
        }

        String status;

        if (player.isFlying() || player.getAllowFlight())
        {
            status = "disabled";
            player.setAllowFlight(false);
            player.setFlying(false);
        }
        else
        {
            status = "enabled";
            player.setAllowFlight(true);
            player.setFlying(true);
        }

        if (player != sender.getSender())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others", true, false,
                    "{target}", player.getName(), "{status}", status);
        }
        DesireEssentials.getLangHandler().sendRenderMessage(player, name.toLowerCase() + ".self", true, false, "{status}", status);
    }
}
