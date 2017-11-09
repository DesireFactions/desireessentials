package com.desiremc.essentials.parsers;

import java.util.Arrays;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.ArgumentParser;
import com.desiremc.essentials.DesireEssentials;

public class GamemodeParser implements ArgumentParser
{

    @Override
    public Object parseArgument(CommandSender sender, String label, String arg)
    {
        if (Arrays.asList(new String[] { "0", "surv", "s", "survival" }).contains(arg))
        {
            return GameMode.SURVIVAL;
        }
        else if (Arrays.asList(new String[] { "1", "creative", "c" }).contains(arg))
        {
            return GameMode.CREATIVE;
        }
        else if (Arrays.asList(new String[] { "2", "adventure", "a" }).contains(arg))
        {
            return GameMode.ADVENTURE;
        }

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "gamemode.invalid");
        return null;
    }

}
