package com.desiremc.essentials.commands;

import com.desiremc.core.session.Rank;
import com.desiremc.hcf.listener.classes.ClassListener;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class ClearInventoryCommand extends PlayerChangeCommand
{
    public ClearInventoryCommand()
    {
        super("clearinventory", "Clears your inventory", Rank.GUEST, new String[] {}, "ci");
    }

    @Override
    public Object[] applyChanges(Player p, Object[] args)
    {

        HCFSession session = HCFSessionHandler.getHCFSession(p.getUniqueId());
        session.setPvpClass(null);
        ClassListener.updateClass(p, p.getInventory().getHelmet());

        p.getInventory().setContents(new ItemStack[p.getInventory().getContents().length]);
        p.getInventory().setArmorContents(new ItemStack[p.getInventory().getArmorContents().length]);

        p.updateInventory();

        return new Object[] {};
    }
}
