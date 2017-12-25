package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.utils.StringUtils;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.parsers.FSessionParser;
import com.desiremc.hcf.session.FSession;
import com.desiremc.hcf.session.FSessionHandler;

import java.util.List;

public class BalanceCommand extends ValidCommand
{

    public BalanceCommand()
    {
        super("balance", "Checks your balance", Rank.GUEST, true, new String[] { "bal", "money" });

        addArgument(CommandArgumentBuilder.createBuilder(FSession.class)
                .setName("target")
                .setParser(new FSessionParser())
                .setRequiredRank(Rank.HELPER)
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String label[], List<CommandArgument<?>> args)
    {
        FSession session;
        if (!args.get(0).hasValue())
        {
            session = FSessionHandler.getOnlineFSession(sender.getUniqueId());
            DesireEssentials.getLangHandler().sendRenderMessage(session.getPlayer(), "balance.self", true, false,
                    "{balance}", StringUtils.formatNumber(session.getBalance(), 2, true));
        }
        else
        {
            session = (FSession) args.get(0).getValue();
            DesireEssentials.getLangHandler().sendRenderMessage(sender, "balance.others", true, false,
                    "{balance}", StringUtils.formatNumber(session.getBalance(), 2, true),
                    "{player}", session.getName());
        }

    }

}
