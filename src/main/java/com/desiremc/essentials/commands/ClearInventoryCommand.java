package com.desiremc.essentials.commands;

import com.desiremc.core.session.Rank;
import org.bukkit.entity.Player;


public class ClearInventoryCommand extends PlayerChangeCommand
{
    public ClearInventoryCommand()
    {
        super("clearinventory", "Clears your inventory", Rank.GUEST, new String[] {}, "ci");
    }

    @Override
    public Object[] applyChanges(Player p, Object[] args)
    {
        p.getInventory().clear();

        return new Object[] {};
    }
}
