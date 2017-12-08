package com.desiremc.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.session.SessionHandler;
import com.desiremc.essentials.DesireEssentials;

public class ListCommand extends ValidCommand
{

    public ListCommand()
    {
        super("list", "List online players.", Rank.GUEST, new String[] {}, new String[] { "who" });
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        DesireEssentials.getLangHandler().sendRenderMessage(sender, "list.format",
                "{online}", Bukkit.getOnlinePlayers().size(),
                "{slots}", Bukkit.getMaxPlayers(),
                "{staff}", getOnlineStaff());
    }

    private String getOnlineStaff()
    {
        StringBuilder sb = new StringBuilder();

        for (Session s : SessionHandler.getStaff())
        {
            sb.append("§e" + s.getName() + ", ");
        }
        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }
        else
        {
            sb.append("§eNone");
        }
        return sb.toString();
    }

}
