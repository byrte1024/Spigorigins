package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class Fox extends Origin {
    public Fox() {
        super("Fox","FOX","The swift creatures of the forest!", Arrays.asList(new String[]{"75% faster"}), Arrays.asList(new String[]{"Can only eat sweet berries","Saturation is limited at 10"}),Material.SWEET_BERRIES,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.175);
        if(player.getSaturation()>10){
            player.setSaturation(10);
        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
