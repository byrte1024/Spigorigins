package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Bee extends Origin {
        public Bee() {
            super("Bee","BEE","The wonderful flying creatures of the forest. they eat flowers and can fly high above", Collections.singletonList("can fly 20 blocks above the ground"), Arrays.asList("can only eat flowers","3 less hearts"), Material.HONEYCOMB,' ');
        }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player==null){
            return;
        }
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(14);
        if(player.getLocation().getBlockY()-player.getWorld().getHighestBlockYAt(player.getLocation())<20){
            player.setAllowFlight(true);
        }
        else{
            player.setFlying(false);
            player.setAllowFlight(false);
        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
