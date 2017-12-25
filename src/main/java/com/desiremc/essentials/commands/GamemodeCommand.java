package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.parsers.GamemodeParser;
import org.apache.commons.lang.StringUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.List;

public class GamemodeCommand extends ValidCommand
{

    public GamemodeCommand()
    {
        super("gamemode", "Changes your gamemode.", Rank.ADMIN, new String[] {"gm"});

        addArgument(CommandArgumentBuilder.createBuilder(GameMode.class)
                .setName("gamemode")
                .setParser(new GamemodeParser())
                .build());

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
        GameMode gameMode = (GameMode) arguments.get(0).getValue();

        if (arguments.get(1).hasValue())
        {
            player = (Player) arguments.get(1).getValue();
        }
        else
        {
            player = sender.getPlayer();
        }

        if (player != sender.getSender())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others", true, false,
                    "{gamemode}", StringUtils.capitalize(gameMode.name().toLowerCase()),
                    "{target}", player.getName());
        }
        DesireEssentials.getLangHandler().sendRenderMessage(player, name.toLowerCase() + ".self", true, false,
                "{gamemode}", StringUtils.capitalize(gameMode.name().toLowerCase()));

        player.setGameMode(gameMode);
    }

}
