package com.desiremc.essentials.commands;

import com.desiremc.hcf.DesireHCF;
import com.desiremc.hcf.listener.classes.ClassListener;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;
import org.bukkit.Bukkit;
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

        HCFSession session = HCFSessionHandler.getHCFSession(p.getUniqueId());

        if(session.getPvpClass() != null)
        {
            ClassListener.applyPermanentEffects(session.getPvpClass(), p);
        }

        return new Object[] {};
    }

}
