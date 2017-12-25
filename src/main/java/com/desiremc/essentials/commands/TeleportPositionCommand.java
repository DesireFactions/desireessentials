package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.IntegerParser;
import com.desiremc.core.parsers.WorldParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportPositionCommand extends ValidCommand
{
    public TeleportPositionCommand()
    {
        super("teleportposition", "Teleport to another position", Rank.HELPER, true, new String[] {"tppos"});

        addArgument(CommandArgumentBuilder.createBuilder(Integer.class)
                .setName("x")
                .setParser(new IntegerParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Integer.class)
                .setName("y")
                .setParser(new IntegerParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Integer.class)
                .setName("x")
                .setParser(new IntegerParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(World.class)
                .setName("world")
                .setParser(new WorldParser())
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player player = sender.getPlayer();

        double x = Double.parseDouble((String) arguments.get(0).getValue());
        double y = Double.parseDouble((String) arguments.get(1).getValue());
        double z = Double.parseDouble((String) arguments.get(2).getValue());

        Location loc = player.getLocation().clone();

        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);

        if (arguments.size() > 3)
        {
            loc.setWorld((World) arguments.get(3).getValue());
        }

        player.teleport(loc);

        if (arguments.size() > 3)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport_position.world", true, false, "{x}", loc.getX(), "{y}", loc.getY(), "{z}", loc.getZ(), "{world}", loc.getWorld().getName());
        }
        else
        {
            DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport_position.no_world", true, false, "{x}", loc.getX(), "{y}", loc.getY(), "{z}", loc.getZ());
        }
    }

}
