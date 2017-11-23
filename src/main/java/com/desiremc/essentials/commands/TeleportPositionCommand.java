package com.desiremc.essentials.commands;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.IntegerParser;
import com.desiremc.core.parsers.StringParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.BukkitWorldValidator;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportPositionCommand extends ValidCommand
{
    public TeleportPositionCommand()
    {
        super("teleportposition", "Teleport to another position", Rank.JRMOD, ARITY_OPTIONAL, new String[] {"x", "y", "z", "world"}, "tppos");

        addParser(new IntegerParser(), "x");
        addParser(new IntegerParser(), "y");
        addParser(new IntegerParser(), "z");
        addParser(new StringParser(), "world");

        addValidator(new PlayerValidator());
        addValidator(new BukkitWorldValidator(), "world");
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player player = (Player) sender;

        int x = (int) args[0];
        int y = (int) args[1];
        int z = (int) args[2];

        Location loc;

        if (args.length > 3)
        {
            World world = Bukkit.getWorld((String) args[3]);
            loc = new Location(world, x, y, z);
        }
        else
        {
            loc = new Location(player.getWorld(), x, y, z);
        }

        player.teleport(loc);

        DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport-position.self", "{x}", loc.getX(), "{y}", loc.getY(), "{z}", loc.getZ(), "{world}", loc.getWorld().getName());
    }

}
