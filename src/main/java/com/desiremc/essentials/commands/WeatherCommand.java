package com.desiremc.essentials.commands;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.desiremc.core.api.WeatherType;
import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.WeatherParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;

public class WeatherCommand extends ValidCommand
{

    public WeatherCommand()
    {
        super("weather", "Change the weather.", Rank.ADMIN, new String[] { "weather", "duration" });
        
        addParser(new WeatherParser(), "weather");
        addValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player player = (Player) sender;
        
        World world = player.getWorld();
        WeatherType weather = (WeatherType) args[0];
        
        
    }
    
}
