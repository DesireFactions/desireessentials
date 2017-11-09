package com.desiremc.essentials.commands;

import org.bukkit.entity.Player;

import com.desiremc.core.parsers.IntegerParser;
import com.desiremc.core.session.Rank;
import com.desiremc.core.validators.IntegerSizeValidator;

public class SpeedCommand extends PlayerChangeCommand
{

    public SpeedCommand()
    {
        super("speed", "Change your speed.", Rank.ADMIN, new String[] { "speed" }, new String[] {});

        addParser(new IntegerParser(), "speed");

        addValidator(new IntegerSizeValidator(1, 10), "speed");
    }

    @Override
    public Object[] applyChanges(Player p, Object[] args)
    {
        String type;
        float speed = (int) args[0];
        if (p.isFlying())
        {
            p.setFlySpeed(getSpeed(speed, true));
            type = "flying";
        }
        else
        {
            p.setWalkSpeed(getSpeed(speed, false));
            type = "walking";
        }
        return new Object[] { "{speed}", args[0], "{type}", type };
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
