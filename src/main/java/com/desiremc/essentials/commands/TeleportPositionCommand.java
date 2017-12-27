package com.desiremc.essentials.commands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.DoubleParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;

public class TeleportPositionCommand extends ValidCommand
{
    public TeleportPositionCommand()
    {
        super("teleportposition", "Teleport to another position", Rank.HELPER, true, new String[] { "tppos" });

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("x")
                .setParser(new DoubleParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("y")
                .setParser(new DoubleParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("x")
                .setParser(new DoubleParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("pitch")
                .setParser(new DoubleParser())
                .setOptional()
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("yaw")
                .setParser(new DoubleParser())
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player player = sender.getPlayer();

        double x = (Double) arguments.get(0).getValue();
        double y = (Double) arguments.get(1).getValue();
        double z = (Double) arguments.get(2).getValue();

        Location loc = player.getLocation().clone();

        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);

        if (arguments.get(3).hasValue())
        {
            loc.setPitch(((Double) arguments.get(3).getValue()).floatValue());
            if (arguments.get(4).hasValue())
            {
                loc.setYaw(((Double) arguments.get(4).getValue()).floatValue());
            }
        }

        player.teleport(loc);

        DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport_position.world", true, false,
                "{x}", loc.getX(),
                "{y}", loc.getY(),
                "{z}", loc.getZ(),
                "{world}", loc.getWorld().getName());
    }

}
