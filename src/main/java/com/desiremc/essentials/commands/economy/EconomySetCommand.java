package com.desiremc.essentials.commands.economy;

import java.util.List;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.DoubleParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.utils.StringUtils;
import com.desiremc.core.validators.NumberSizeValidator;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.parsers.FSessionParser;
import com.desiremc.hcf.session.FSession;

public class EconomySetCommand extends ValidCommand
{

    public EconomySetCommand()
    {
        super("set", "Set a player's money.", Rank.ADMIN);

        addArgument(CommandArgumentBuilder.createBuilder(FSession.class)
                .setName("target")
                .setParser(new FSessionParser())
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Double.class)
                .setName("amount")
                .setParser(new DoubleParser())
                .addValidator(new NumberSizeValidator<Double>(0.0, Double.MAX_VALUE))
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        FSession target = (FSession) arguments.get(0).getValue();
        double amount = (Double) arguments.get(1).getValue();

        target.setBalance(amount);
        target.save();

        if (sender == target.getSession())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "economy.set.self", true, false,
                    "{amount}", StringUtils.formatNumber(amount, 2, true));
        }
        else
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "economy.set.confirm", true, false,
                    "{amount}", StringUtils.formatNumber(amount, 2, true),
                    "{target}", target.getName());

            DesireEssentials.getLangHandler().sendRenderMessage(target, "economy.set.target", true, false,
                    "{amount}", StringUtils.formatNumber(amount, 2, true));
        }
    }

}
