package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

public class Werewolf extends Origin {
    public Werewolf(){
        super("WereWolf","WEREWOLF","these foxes are fairly week. but when the moon shines. they are at their strongest", Arrays.asList(new String[]{"25% faster at night","4 hearts more at night","25% stronger at night"}), Arrays.asList(new String[]{"25% slower at day","4 hearts less at day","25% weaker at day"}), Material.BONE,' ');
    }


    @Override
    public void applyEffects(Player player, Asigner asigner) {
        long time = player.getWorld().getTime();

        boolean night = time < 12300 || time > 23850;
        if(!night){
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.125);
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1.25);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(28);
        }
        else{
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.075);
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(0.75);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(12);
        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
