package com.desiremc.essentials.commands;

import com.desiremc.core.api.newcommands.CommandArgument;
import com.desiremc.core.api.newcommands.CommandArgumentBuilder;
import com.desiremc.core.api.newcommands.ValidCommand;
import com.desiremc.core.parsers.PlayerParser;
import com.desiremc.core.parsers.PositiveIntegerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.session.Session;
import com.desiremc.core.validators.NumberSizeValidator;
import com.desiremc.essentials.DesireEssentials;
import org.bukkit.entity.Player;

import java.util.List;

public class SpeedCommand extends ValidCommand
{

    public SpeedCommand()
    {
        super("speed", "Change your speed.", Rank.ADMIN);

        addArgument(CommandArgumentBuilder.createBuilder(Integer.class)
                .setName("speed")
                .setParser(new PositiveIntegerParser())
                .addValidator(new NumberSizeValidator<>(1, 10))
                .build());

        addArgument(CommandArgumentBuilder.createBuilder(Player.class)
                .setName("target")
                .setParser(new PlayerParser())
                .setAllowsConsole()
                .setOptional()
                .build());
    }

    @Override
    public void validRun(Session sender, String[] label, List<CommandArgument<?>> arguments)
    {
        Player player;
        int speed = (Integer) arguments.get(0).getValue();

        if (arguments.get(1).hasValue())
        {
            player = (Player) arguments.get(1).getValue();
        }
        else
        {
            player = sender.getPlayer();
        }

        String type;
        if (player.isFlying())
        {
            player.setFlySpeed(getSpeed(speed, true));
            type = "flying";
        }
        else
        {
            player.setWalkSpeed(getSpeed(speed, false));
            type = "walking";
        }

        if (player != sender.getSender())
        {
            DesireEssentials.getLangHandler().sendRenderMessage(sender, name.toLowerCase() + ".others", true, false,
                    "{speed}", speed,
                    "{type}", type,
                    "{target}", player.getName());
        }
        DesireEssentials.getLangHandler().sendRenderMessage(player, name.toLowerCase() + ".self", true, false,
                "{speed}", speed,
                "{type}", type);

    }

    private float getSpeed(float userSpeed, final boolean flying)
    {
        final float defaultSpeed = flying ? 0.1f : 0.2f;
        float maxSpeed = 1f;

        if (userSpeed < 1f)
        {
            return defaultSpeed * userSpeed;
        }
        else
        {
            float ratio = ((userSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
            return ratio + defaultSpeed;
        }
    }

}
