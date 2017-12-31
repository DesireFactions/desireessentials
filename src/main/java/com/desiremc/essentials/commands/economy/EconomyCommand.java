package com.desiremc.essentials.commands.economy;

import com.desiremc.core.api.newcommands.ValidBaseCommand;

public class EconomyCommand extends ValidBaseCommand
{

    public EconomyCommand()
    {
        super("economy", "Manage the economy.", new String[] { "eco", "econ" });

        addSubCommand(new EconomyGiveCommand());
        addSubCommand(new EconomyTakeCommand());
        addSubCommand(new EconomySetCommand());
    }

}
