package com.desiremc.essentials.parsers;

import com.desiremc.core.api.newcommands.Parser;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.GameMode;

import java.util.Arrays;
import java.util.List;

public class GamemodeParser implements Parser<GameMode>
{

    @Override
    public GameMode parseArgument(Session sender, String[] label, String rawArgument)
    {
        if (Arrays.asList(new String[] {"0", "surv", "s", "survival"}).contains(rawArgument))
        {
            return GameMode.SURVIVAL;
        }
        else if (Arrays.asList(new String[] {"1", "creative", "c"}).contains(rawArgument))
        {
            return GameMode.CREATIVE;
        }
        else if (Arrays.asList(new String[] {"2", "adventure", "a"}).contains(rawArgument))
        {
            return GameMode.ADVENTURE;
        }

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "gamemode.invalid");
        return null;
    }

    @Override
    public List<String> getRecommendations(Session sender, String lastWord)
    {
        return null;
    }

}
