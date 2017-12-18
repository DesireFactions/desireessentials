package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.newparsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportHereCommand extends ValidCommand
{

    public TeleportHereCommand()
    {
        super("teleporthere", "Teleport another player to you", Rank.HELPER, true, new String[] {"tphere"});

        addArgument(CommandArgumentBuilder.createBuilder(Player.class)
                .setName("target")
                .setParser(new PlayerParser())
                .setAllowsConsole()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player target = (Player) arguments.get(0).getValue();
        Player player = (Player) sender;

        target.teleport(player);

        DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport-here.self",
                "{target}", target.getName());
        DesireEssentials.getLangHandler().sendRenderMessage(target, "teleport-here.target",
                "{target}", player.getName());
    }

}
