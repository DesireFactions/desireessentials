package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.parsers.HCFSessionParser;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;

import java.util.List;

public class BalanceCommand extends ValidCommand
{

    public BalanceCommand()
    {
        super("balance", "Checks your balance", Rank.GUEST, true, new String[] {"bal", "money"});

        addArgument(CommandArgumentBuilder.createBuilder(HCFSession.class)
                .setName("target")
                .setParser(new HCFSessionParser())
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String label[], List<CommandArgument<?>> args)
    {
        HCFSession session;
        if (!args.get(0).hasValue())
        {
            session = HCFSessionHandler.getHCFSession(sender.getUniqueId());
            DesireEssentials.getLangHandler().sendRenderMessage(session.getPlayer(), "balance.self", "{balance}", DesireEssentials.getEconomy().getBalance(session.getPlayer()));
        }
        else
        {
            session = (HCFSession) args.get(0).getValue();
            DesireEssentials.getLangHandler().sendRenderMessage(session.getPlayer(), "balance.others", "{balance}", DesireEssentials.getEconomy().getBalance(session.getPlayer()), "{player}", session.getName());
        }

    }

}
