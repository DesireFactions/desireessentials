package com.desiremc.essentials.commands;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Sound;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.SessionParser;
import com.desiremc.core.parsers.StringParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.validators.SenderCanMessageTarget;

public class MessageCommand extends ValidCommand
{

    public static HashMap<UUID, UUID> history = new HashMap<>();

    public MessageCommand()
    {
        super("message", "Message another player", Rank.GUEST, true, new String[] {"msg", "m"});

        addArgument(CommandArgumentBuilder.createBuilder(Session.class)
                .setName("target")
                .setParser(new SessionParser())
                .addValidator(new SenderCanMessageTarget())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(String.class)
                .setName("message")
                .setParser(new StringParser())
                .setVariableLength()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> args)
    {
        Session target = (Session) args.get(0).getValue();
        String message = (String) args.get(1).getValue();

        history.put(sender.getUniqueId(), target.getUniqueId());
        history.put(target.getUniqueId(), sender.getUniqueId());

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "message.sending", false, false,
                "{rankColor}", target.getRank().getColor().toString(),
                "{player}", target.getName(),
                "{message}", message);

        DesireEssentials.getLangHandler().sendRenderMessage(target, "message.receiving", false, false,
                "{rankColor}", sender.getRank().getColor().toString(),
                "{player}", sender.getName(),
                "{message}", message);

        target.getPlayer().playSound(target.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
    }
}
