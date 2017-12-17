package com.desiremc.essentials;

import com.desiremc.core.api.FileHandler;
import com.desiremc.core.api.LangHandler;
import com.desiremc.core.api.command.CustomCommandHandler;
import com.desiremc.core.api.newcommands.CommandHandler;
import com.desiremc.essentials.commands.BalanceCommand;
import com.desiremc.essentials.commands.ClearInventoryCommand;
import com.desiremc.essentials.commands.FeedCommand;
import com.desiremc.essentials.commands.GamemodeCommand;
import com.desiremc.essentials.commands.HealCommand;
import com.desiremc.essentials.commands.HelpCommand;
import com.desiremc.essentials.commands.ListCommand;
import com.desiremc.essentials.commands.MessageCommand;
import com.desiremc.essentials.commands.PayCommand;
import com.desiremc.essentials.commands.RespondCommand;
import com.desiremc.essentials.commands.SpeedCommand;
import com.desiremc.essentials.commands.TeleportCommand;
import com.desiremc.essentials.commands.TeleportHereCommand;
import com.desiremc.essentials.commands.TeleportPositionCommand;
import com.desiremc.essentials.commands.weather.WeatherClearCommand;
import com.desiremc.essentials.commands.weather.WeatherCommand;
import com.desiremc.essentials.commands.weather.WeatherDownfallCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DesireEssentials extends JavaPlugin
{

    public static DesireEssentials instance;

    private static LangHandler lang;
    private static FileHandler config;

    private static Economy econ;

    public void onEnable()
    {
        instance = this;

        registerFiles();
        registerCommands();
        registerListeners();
    }

    private void registerFiles()
    {
        // make sure the lang file does not exist before trying to save it.
        File langFile = new File(getDataFolder(), "lang.yml");
        if (!langFile.exists())
        {
            saveResource("lang.yml", false);
        }
        lang = new LangHandler(langFile, this);

        // this performs the check for us, don't need to do the same as lang.
        saveDefaultConfig();
        config = new FileHandler(new File(getDataFolder(), "config.yml"), this);
    }

    private void registerCommands()
    {
        CustomCommandHandler customCommandHandler = CustomCommandHandler.getInstance();
        customCommandHandler.registerCommand(new BalanceCommand(), this);
        customCommandHandler.registerCommand(new PayCommand(), this);
        customCommandHandler.registerCommand(new FeedCommand(), this);
        customCommandHandler.registerCommand(new HelpCommand(), this);
        customCommandHandler.registerCommand(new GamemodeCommand(), this);
        customCommandHandler.registerCommand(new HealCommand(), this);
        customCommandHandler.registerCommand(new SpeedCommand(), this);
        customCommandHandler.registerCommand(new ClearInventoryCommand(), this);
        customCommandHandler.registerCommand(new TeleportCommand(), this);
        customCommandHandler.registerCommand(new TeleportHereCommand(), this);
        customCommandHandler.registerCommand(new TeleportPositionCommand(), this);

        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.registerCommand(new ListCommand(), this);
        commandHandler.registerCommand(new WeatherCommand(), this);
        commandHandler.registerCommand(new WeatherClearCommand(), this);
        commandHandler.registerCommand(new WeatherDownfallCommand(), this);
        commandHandler.registerCommand(new MessageCommand(), this);
        commandHandler.registerCommand(new RespondCommand(), this);
    }

    private void registerListeners()
    {

    }

    public static Economy getEconomy()
    {
        if (econ == null)
        {
            econ = Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
        }
        return econ;
    }

    public static FileHandler getConfigHandler()
    {
        return config;
    }

    public static LangHandler getLangHandler()
    {
        return lang;
    }

    public static DesireEssentials getInstance()
    {
        return instance;
    }

}
