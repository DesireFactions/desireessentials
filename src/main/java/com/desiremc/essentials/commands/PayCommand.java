package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.newparsers.PositiveDoubleParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.validators.HasEnoughMoneyValidator;
import com.desiremc.hcf.parsers.HCFSessionParser;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;

import java.util.List;

public class PayCommand extends ValidCommand
{

    public PayCommand()
    {
        super("pay", "Send a player money.", Rank.GUEST, new String[] {"target", "amount"});

        addArgument(CommandArgumentBuilder.createBuilder(HCFSession.class)
                .setName("target")
                .setParser(new HCFSessionParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("amount")
                .setParser(new PositiveDoubleParser())
                .addValidator(new HasEnoughMoneyValidator())
                .build());
    }

    @Override
    public void validRun(Session sender, String label[], List<CommandArgument<?>> args)
    {
        HCFSession session = HCFSessionHandler.getHCFSession(sender.getUniqueId());
        HCFSession target = (HCFSession) args.get(0).getValue();
        double amount = Double.parseDouble((String) args.get(1).getValue());

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
