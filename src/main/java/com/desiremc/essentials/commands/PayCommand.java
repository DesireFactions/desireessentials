package com.desiremc.essentials.commands;

import java.util.List;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.parsers.PositiveDoubleParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.NumberSizeValidator;
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
                .addValidator(new NumberSizeValidator<>(1.0, 1000000.0))
                .build());
    }

    @Override
    public void validFactionRun(FSession sender, String label[], List<CommandArgument<?>> args)
    {
        FSession target = (FSession) args.get(0).getValue();
        double amount = ((Number) args.get(1).getValue()).doubleValue();

        if (!sender.isConsole())
        {
            sender.withdrawBalance(amount);
        }

        target.depositBalance(amount);

        DesireEssentials.getLangHandler().sendRenderMessage(sender, "pay.sent", true, false,
                "{amount}", amount,
                "{player}", target.getName());

        DesireEssentials.getLangHandler().sendRenderMessage(target, "pay.received", true, false,
                "{amount}", amount,
                "{player}", sender.getName());
    }

}
