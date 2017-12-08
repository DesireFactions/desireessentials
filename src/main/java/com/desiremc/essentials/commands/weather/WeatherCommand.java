package com.desiremc.essentials.commands.weather;

import java.util.List;

import org.bukkit.World;

import com.desiremc.core.api.newcommands.ValidBaseCommand;
import com.desiremc.core.session.Rank;

public class WeatherCommand extends ValidBaseCommand
{

    public WeatherCommand()
    {
        super("weather", "Change the weather.", Rank.ADMIN);

        addSubCommand(new WeatherClearCommand());
        addSubCommand(new WeatherDownfallCommand());

    }

    protected static String worldsString(List<World> worlds)
    {
        if (worlds.size() == 1)
        {
            return worlds.get(0).getName();
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < worlds.size() - 1; i++)
            {
                sb.append(worlds.get(i) + ", ");
            }
            sb.append("and " + worlds.get(worlds.size() - 1).getName());
            return sb.toString();
        }
    }
}
