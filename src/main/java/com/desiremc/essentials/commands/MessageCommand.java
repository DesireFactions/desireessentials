package com.desiremc.essentials.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.parsers.StringParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.session.SessionHandler;
import com.desiremc.core.validators.NotIgnoringValidator;
import com.desiremc.essentials.DesireEssentials;

public class MessageCommand extends ValidCommand
{

    private static MessageCommand instance;
    
    public static HashMap<UUID, UUID> history = new HashMap<>();

    public MessageCommand()
    {
        super("message", "Message another player", Rank.GUEST, ARITY_REQUIRED_VARIADIC, new String[] { "target", "message" }, new String[] { "msg", "m", "tell", "whisper", "whisp", "w", "t" });

        instance = this;
        
        addParser(new PlayerParser(), "target");
        addParser(new StringParser(), "message");

        addValidator(new NotIgnoringValidator(), "target");
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        Player target = (Player) args[0];
        String message = (String) args[1];

        Session senderSession = SessionHandler.getSession(sender);
        Session receiverSession = SessionHandler.getSession(target);

        history.put(senderSession.getUniqueId(), receiverSession.getUniqueId());
        history.put(receiverSession.getUniqueId(), senderSession.getUniqueId());

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "message.sending",
                "{rankColor}", receiverSession.getRank().getColor().toString(),
                "{player}", receiverSession.getName(),
                "{message}", message);

        DesireEssentials.getLangHandler().sendRenderMessage(receiverSession, "message.receiving",
                "{rankColor}", senderSession.getRank().getColor().toString(),
                "{player}", senderSession.getRank().getColor().toString(),
                "{message}", message);
    }
    
    public static MessageCommand getInstance()
    {
        return instance;
    }

}
