package com.desiremc.essentials.commands;

import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.parsers.DoubleParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.DoubleSizeValidator;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.validators.HasEnoughMoneyValidator;
import com.desiremc.hcf.parser.PlayerHCFSessionParser;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;

public class PayCommand extends ValidCommand
{

    public PayCommand()
    {
        super("pay", "Send a player money.", Rank.GUEST, new String[] { "target", "amount" });

        addParser(new PlayerHCFSessionParser(), "target");
        addParser(new DoubleParser(), "amount");

        addValidator(new DoubleSizeValidator(0, Double.MAX_VALUE), "amount");
        addValidator(new HasEnoughMoneyValidator(), "amount");
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        HCFSession session = HCFSessionHandler.getHCFSession(sender);
        HCFSession target = (HCFSession) args[0];
        double amount = (double) args[1];

        session.withdrawBalance(amount);
        target.depositBalance(amount);

        DesireEssentials.getLangHandler().sendRenderMessage(session.getPlayer(), "pay.sent",
                "{amount}", amount,
                "{player}", target.getName());

        DesireEssentials.getLangHandler().sendRenderMessage(target.getPlayer(), "pay.received",
                "{amount}", amount,
                "{player}", session.getName());
    }

}
