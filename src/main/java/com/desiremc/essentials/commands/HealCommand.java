package com.desiremc.essentials.commands;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.desiremc.core.session.Rank;

public class HealCommand extends PlayerChangeCommand
{

    public HealCommand()
    {
        super("heal", "Heal yourself completely.", Rank.ADMIN, new String[] {}, new String[] {});
    }

    @Override
    public Object[] applyChanges(Player p, Object[] args)
    {
        p.setFoodLevel(20);
        p.setSaturation(10);
        p.setExhaustion(0);
        p.setHealth(p.getMaxHealth());
        p.setFireTicks(0);
        for (PotionEffect effect : p.getActivePotionEffects())
        {
            p.removePotionEffect(effect.getType());
        }

        return new Object[] {};
    }

}
