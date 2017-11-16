package com.desiremc.essentials.commands;

import org.bukkit.command.CommandSender;

import com.desiremc.core.api.command.ValidCommand;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.PlayerValidator;
import com.desiremc.essentials.DesireEssentials;
import com.desiremc.hcf.parser.PlayerHCFSessionParser;
import com.desiremc.hcf.session.HCFSession;
import com.desiremc.hcf.session.HCFSessionHandler;

public class BalanceCommand extends ValidCommand
{

    public BalanceCommand()
    {
        super("balance", "Checks your balance", Rank.GUEST, ARITY_OPTIONAL, new String[] { "target" }, new String[] { "bal", "money" });

        addValidator(new PlayerValidator());

        addParser(new PlayerHCFSessionParser(), "target");
    }

    @Override
    public void validRun(CommandSender sender, String label, Object... args)
    {
        HCFSession session;
        if (args.length == 0)
        {
            session = HCFSessionHandler.getHCFSession(sender);
            DesireEssentials.getLangHandler().sendRenderMessage(session.getPlayer(), "balance.self", "{balance}", DesireEssentials.getEconomy().getBalance(session.getPlayer()));
        }
        else
        {
            session = (HCFSession) args[0];
            DesireEssentials.getLangHandler().sendRenderMessage(session.getPlayer(), "balance.others", "{balance}", DesireEssentials.getEconomy().getBalance(session.getPlayer()), "{player}", session.getName());
        }

    }

}
