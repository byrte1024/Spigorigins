package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class Werewolf extends Origin {
    public Werewolf(){
        super("WereWolf","WEREWOLF","these foxes are fairly week. but when the moon shines. they are at their strongest", Arrays.asList("25% faster at night","4 hearts more at night","25% stronger at night"), Arrays.asList("25% slower at day","4 hearts less at day","25% weaker at day"), Material.BONE,' ');
    }


    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player==null){
            return;
        }
        long time = player.getWorld().getTime();

        boolean night = time < 12300 || time > 23850;
        if(!night){
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.125);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1.25);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(28);
        }
        else{
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.075);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(0.75);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(12);
        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
