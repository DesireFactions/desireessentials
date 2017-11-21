package com.desiremc.essentials.commands;


import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHereCommand extends ValidCommand
{

    public TeleportHereCommand()
    {
        super("teleporthere", "Teleport another player to you", Rank.JRMOD, ARITY_REQUIRED_VARIADIC, new String[] { "target" }, "tphere");

        addParser(new PlayerParser(), "target");
        addValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player target = (Player) args[0];
        Player player = (Player) sender;

        target.teleport(player);

        DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport-here.self",
                "{target}", target.getName());
        DesireEssentials.getLangHandler().sendRenderMessage(target, "teleport-here.target",
                "{target}", player.getName());
    }

}
