package com.desiremc.essentials.commands;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.listener.classes.ClassListener;
import com.desiremc.hcf.session.FSession;
import com.desiremc.hcf.session.FSessionHandler;

public class HealCommand extends ValidCommand
{

    public HealCommand()
    {
        super("heal", "Heal yourself completely.", Rank.ADMIN, true, new String[] {});

        addArgument(CommandArgumentBuilder.createBuilder(Player.class)
                .setName("target")
                .setParser(new PlayerParser())
                .setAllowsConsole()
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player player;

        if (arguments.get(0).hasValue())
        {
            player = (Player) arguments.get(0).getValue();
        }
        else
        {
            player = sender.getPlayer();
        }

        if (player != sender.getSender())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others", true, false,
                    "{target}", player.getName());
        }
        DesireEssentials.getLangHandler().sendRenderMessage(player, name.toLowerCase() + ".self", true, false);

        player.setFoodLevel(20);
        player.setSaturation(10);
        player.setExhaustion(0);
        player.setHealth(player.getMaxHealth());
        player.setFireTicks(0);
        for (PotionEffect effect : player.getActivePotionEffects())
        {
            player.removePotionEffect(effect.getType());
        }

        FSession session = FSessionHandler.getOnlineFSession(player.getUniqueId());

        if (session.getPvpClass() != null)
        {
            ClassListener.applyPermanentEffects(session.getPvpClass(), player);
        }
    }

}
