package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.newparsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.listener.classes.ClassListener;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class HealCommand extends ValidCommand
{

    public HealCommand()
    {
        super("heal", "Heal yourself completely.", Rank.ADMIN, new String[] {});

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
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others",
                    "{target}", player.getName());
        }
        DesireEssentials.getLangHandler().sendRenderMessage(player, name.toLowerCase() + ".self");

        player.setFoodLevel(20);
        player.setSaturation(10);
        player.setExhaustion(0);
        player.setHealth(player.getMaxHealth());
        player.setFireTicks(0);
        for (PotionEffect effect : player.getActivePotionEffects())
        {
            player.removePotionEffect(effect.getType());
        }

        HCFSession session = HCFSessionHandler.getHCFSession(player.getUniqueId());

        if (session.getPvpClass() != null)
        {
            ClassListener.applyPermanentEffects(session.getPvpClass(), player);
        }
    }

}
