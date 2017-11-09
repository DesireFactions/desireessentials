package com.desiremc.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.utils.PlayerUtils;

public class RespondCommand extends ValidCommand
{

    public RespondCommand()
    {
        super("respond", "Respond to a message.", Rank.GUEST, ARITY_REQUIRED_VARIADIC, new String[] { "message" }, new String[] { "r", "resp" });
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        MessageCommand.getInstance().validRun(sender, label, new Object[] { Bukkit.getPlayer(MessageCommand.history.get(PlayerUtils.getUUIDFromSender(sender))), args[0] });
    }

}
