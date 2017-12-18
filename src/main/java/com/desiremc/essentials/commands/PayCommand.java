package com.desiremc.essentials.commands;

import java.util.List;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.newparsers.PositiveDoubleParser;
import com.desiremc.core.session.Rank;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.essentials.validators.HasEnoughMoneyValidator;
import com.desiremc.hcf.api.commands.FactionValidCommand;
import com.desiremc.hcf.parsers.FSessionParser;
import com.desiremc.hcf.session.FSession;

public class PayCommand extends FactionValidCommand
{

    public PayCommand()
    {
        super("pay", "Send a player money.", Rank.GUEST, new String[] {"target", "amount"});

        addArgument(CommandArgumentBuilder.createBuilder(FSession.class)
                .setName("target")
                .setParser(new FSessionParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("amount")
                .setParser(new PositiveDoubleParser())
                .addValidator(new HasEnoughMoneyValidator())
                .build());
    }

    @Override
    public void validFactionRun(FSession sender, String label[], List<CommandArgument<?>> args)
    {
        FSession target = (FSession) args.get(0).getValue();
        double amount = Double.parseDouble((String) args.get(1).getValue());

        sender.withdrawBalance(amount);
        target.depositBalance(amount);

        DesireEssentials.getLangHandler().sendRenderMessage(sender.getPlayer(), "pay.sent",
                "{amount}", amount,
                "{player}", target.getName());

        DesireEssentials.getLangHandler().sendRenderMessage(target.getPlayer(), "pay.received",
                "{amount}", amount,
                "{player}", sender.getName());
    }

}
