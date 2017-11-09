package com.desiremc.essentials;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.desiremc.core.api.FileHandler;
import com.desiremc.core.api.LangHandler;
import com.desiremc.core.api.command.CustomCommandHandler;
import com.desiremc.essentials.commands.BalanceCommand;
import com.desiremc.essentials.commands.FeedCommand;
import com.desiremc.essentials.commands.GamemodeCommand;
import com.desiremc.essentials.commands.HelpCommand;
import com.desiremc.essentials.commands.ListCommand;
import com.desiremc.essentials.commands.MessageCommand;
import com.desiremc.essentials.commands.PayCommand;
import com.desiremc.essentials.commands.RespondCommand;

import net.milkbowl.vault.economy.Economy;

public class DesireEssentials extends JavaPlugin
{

    public static DesireEssentials instance;

    private static LangHandler lang;
    private static FileHandler config;

    private static Economy econ;

    public void onEnable()
    {
        instance = this;

        saveResource("lang.yml", false);

        lang = new LangHandler(new File(getDataFolder(), "lang.yml"), this);

        registerCommands();
        registerListeners();
    }

    private void registerCommands()
    {
        CustomCommandHandler handler = CustomCommandHandler.getInstance();
        handler.registerCommand(new BalanceCommand(), this);
        handler.registerCommand(new ListCommand(), this);
        handler.registerCommand(new MessageCommand(), this);
        handler.registerCommand(new PayCommand(), this);
        handler.registerCommand(new RespondCommand(), this);
        handler.registerCommand(new FeedCommand(), this);
        handler.registerCommand(new HelpCommand(), this);
        handler.registerCommand(new GamemodeCommand(), this);
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
