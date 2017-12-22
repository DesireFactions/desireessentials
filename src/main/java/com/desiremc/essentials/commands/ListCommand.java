package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.session.SessionHandler;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.Bukkit;

import java.util.List;

public class ListCommand extends ValidCommand
{

    public ListCommand()
    {
        super("list", "List online players.", Rank.GUEST, new String[] { "who", "ls" });
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        DesireEssentials.getLangHandler().sendRenderMessage(sender, "list.format",
                "{online}", Bukkit.getOnlinePlayers().size(),
                "{slots}", Bukkit.getMaxPlayers(),
                "{staff}", getOnlineStaff());
    }

    private String getOnlineStaff()
    {
        StringBuilder sb = new StringBuilder();

        for (Session s : SessionHandler.getOnlineStaff())
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
