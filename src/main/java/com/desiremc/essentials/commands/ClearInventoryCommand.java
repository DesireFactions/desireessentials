package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.newparsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.listener.classes.ClassListener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class ClearInventoryCommand extends ValidCommand
{

    public ClearInventoryCommand()
    {
        super("clearinventory", "Clears your inventory,", Rank.GUEST, new String[] {"ci"});

        addArgument(CommandArgumentBuilder.createBuilder(Player.class)
                .setName("target")
                .setParser(new PlayerParser())
                .setAllowsConsole()
                .setOptional()
                .setRequiredRank(Rank.ADMIN)
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player player;

        if (arguments.get(1).hasValue())
        {
            player = (Player) arguments.get(1).getValue();
        }
        else
        {
            player = sender.getPlayer();
        }

        if (player != sender.getSender())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others");
        }
        DesireEssentials.getLangHandler().sendRenderMessage(player, name.toLowerCase() + ".self");

        player.getInventory().setContents(new ItemStack[player.getInventory().getContents().length]);
        player.getInventory().setArmorContents(new ItemStack[player.getInventory().getArmorContents().length]);

        player.updateInventory();

        ClassListener.updateClass(player, player.getInventory().getHelmet());
    }
}
