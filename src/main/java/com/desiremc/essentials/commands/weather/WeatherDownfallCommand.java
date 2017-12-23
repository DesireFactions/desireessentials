package com.desiremc.essentials.commands.weather;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.TimeParser;
import com.desiremc.core.parsers.WorldParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.utils.DateUtils;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.desiremc.essentials.commands.weather.WeatherCommand.worldsString;

public class WeatherDownfallCommand extends ValidCommand
{

    public WeatherDownfallCommand()
    {
        super("downfall", "Sets the weather to downfall.", Rank.ADMIN, new String[] { "rain", "storm" });

        addArgument(CommandArgumentBuilder.createBuilder(Long.class)
                .setName("length")
                .setParser(new TimeParser())
                .setOptional()
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(World.class)
                .setName("world")
                .setParser(new WorldParser())
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        List<World> worlds = new ArrayList<>();
        int duration = arguments.get(0).hasValue() ? (Integer) arguments.get(0).getValue() : DesireEssentials.getConfigHandler().getInteger("weather.default_duration") * 1000;
        if (arguments.get(1).hasValue())
        {
            worlds.add((World) arguments.get(1).getValue());
        }
        else if (sender.isPlayer())
        {
            worlds.add(sender.getPlayer().getWorld());
        }
        else
        {
            worlds.addAll(Bukkit.getWorlds());
        }

        for (World world : worlds)
        {
            world.setStorm(true);
            world.setThundering(new Random().nextBoolean());
            world.setWeatherDuration(duration / 50);
        }

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "weather",
                "{weather}", "downfall",
                "{worlds}", worldsString(worlds),
                "{duration}", DateUtils.formatDateDiff(System.currentTimeMillis() + duration));
    }

}
