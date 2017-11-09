package com.desiremc.essentials.commands;

import org.bukkit.entity.Player;

import com.desiremc.core.session.Rank;

public class FeedCommand extends PlayerChangeCommand
{

    public FeedCommand()
    {
        super("feed", "Fill your hunger bar.", Rank.ADMIN, new String[] {}, new String[] { "eat" });
    }

    @Override
    public String[] applyChanges(Player p, Object[] args)
    {
        p.setFoodLevel(20);
        p.setSaturation(10);
        p.setExhaustion(0);
        return new String[] {};
    }

}
