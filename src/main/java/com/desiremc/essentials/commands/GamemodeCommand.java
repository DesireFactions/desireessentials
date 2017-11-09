package com.desiremc.essentials.commands;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.desiremc.core.session.Rank;
import com.desiremc.essentials.parsers.GamemodeParser;

public class GamemodeCommand extends PlayerChangeCommand
{

    public GamemodeCommand()
    {
        super("gamemode", "Changes your gamemode.", Rank.ADMIN, new String[] { "gamemode" }, new String[] { "gm" });

        addParser(new GamemodeParser(), "gamemode");
    }

    @Override
    public Object[] applyChanges(Player p, Object[] args)
    {
        GameMode mode = (GameMode) args[0];
        p.setGameMode(mode);
        return new String[] { "{gamemode}", mode.name().toLowerCase() };
    }

}
