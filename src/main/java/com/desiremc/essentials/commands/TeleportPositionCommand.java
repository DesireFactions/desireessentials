package com.desiremc.essentials.commands;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.DoubleParser;
import com.desiremc.core.parsers.WorldParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportPositionCommand extends ValidCommand
{
    public TeleportPositionCommand()
    {
        super("teleportposition", "Teleport to another position", Rank.HELPER, ARITY_OPTIONAL, new String[] {"x", "y", "z", "world"}, "tppos");

        addParser(new DoubleParser(), "x");
        addParser(new DoubleParser(), "y");
        addParser(new DoubleParser(), "z");
        addParser(new WorldParser(), "world");

        addValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player player = (Player) sender;

        double x = (double) args[0];
        double y = (double) args[1];
        double z = (double) args[2];

        Location loc = player.getLocation().clone();

        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);

        if (args.length > 3)
        {
            loc.setWorld((World) args[3]);
        }

        player.teleport(loc);

        if (args.length > 3)
        {
            DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport_position.world", "{x}", loc.getX(), "{y}", loc.getY(), "{z}", loc.getZ(), "{world}", loc.getWorld().getName());
        }
        else
        {
            DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport_position.no_world", "{x}", loc.getX(), "{y}", loc.getY(), "{z}", loc.getZ());
        }
    }

}
