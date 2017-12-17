package com.desiremc.essentials.commands;


import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand extends ValidCommand
{

    public TeleportCommand()
    {
        super("teleport", "Teleport to another player", Rank.HELPER, ARITY_REQUIRED_VARIADIC, new String[] {"target"}, "tp");

        addParser(new PlayerParser(), "target");
        addValidator(new PlayerValidator());
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player target = (Player) args[0];
        Player player = (Player) sender;

        player.teleport(target);

        DesireEssentials.getLangHandler().sendRenderMessage(player, "teleport.self",
                "{target}", target.getName());
    }

}
