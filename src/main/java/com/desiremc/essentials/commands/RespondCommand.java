package com.desiremc.essentials.commands;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.StringParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.session.SessionHandler;
import com.desiremc.core.utils.PlayerUtils;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.validators.HasMessageHistory;

public class RespondCommand extends ValidCommand
{

    public RespondCommand()
    {
        super("respond", "Respond to a message.", Rank.GUEST, true, new String[] {"r", "reply"});

        addSenderValidator(new HasMessageHistory());

        addArgument(CommandArgumentBuilder.createBuilder(String.class)
                .setName("message")
                .setParser(new StringParser())
                .setVariableLength()
                .build());

    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> args)
    {
        Player target = PlayerUtils.getPlayer(MessageCommand.history.get(sender.getUniqueId()));
        String message = (String) args.get(0).getValue();

        Session receiverSession = SessionHandler.getSession(target);

        MessageCommand.history.put(sender.getUniqueId(), receiverSession.getUniqueId());
        MessageCommand.history.put(receiverSession.getUniqueId(), sender.getUniqueId());

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "message.sending", false, false,
                "{rankColor}", receiverSession.getRank().getColor().toString(),
                "{player}", receiverSession.getName(),
                "{message}", message);

        DesireEssentials.getLangHandler().sendRenderMessage(receiverSession, "message.receiving", false, false,
                "{rankColor}", sender.getRank().getColor().toString(),
                "{player}", sender.getName(),
                "{message}", message);

        target.playSound(target.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
    }

}
