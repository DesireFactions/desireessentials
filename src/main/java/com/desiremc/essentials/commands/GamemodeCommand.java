package com.desiremc.essentials.commands;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.parsers.GamemodeParser;

public class GamemodeCommand extends ValidCommand
{

    public GamemodeCommand()
    {
        super("gamemode", "Changes your gamemode.", Rank.ADMIN, ARITY_OPTIONAL, new String[] { "gamemode", "target" }, "gm");

        addParser(new GamemodeParser(), "gamemode");
        addParser(new PlayerParser(), "target");

        addOptionalNonexistantValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        GameMode mode = (GameMode) args[0];
        Player p;
        if (args.length == 1)
        {
            p = (Player) sender;
        }
        else
        {
            p = (Player) args[1];
        }
        p.setGameMode(mode);

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "gamemode.set",
                "{gamemode}", mode.name().toLowerCase(),
                "{target}", p.getName());
    }

}
