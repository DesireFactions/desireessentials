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

public class TeleportCommand extends ValidCommand
{

    public TeleportCommand()
    {
        super("teleport", "Teleport to another player", Rank.HELPER, true, new String[] {"tp"});

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

        player.teleport(target);

        DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport.self",
                "{target}", target.getName());
    }

}
