package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Bee extends Origin {
        public Bee() {
            super("Bee","BEE","The wonderful flying creatures of the forest. they eat flowers and can fly high above", Arrays.asList(new String[]{"can fly 20 blocks above the ground"}), Arrays.asList(new String[]{"can only eat flowers","has only 6 hearts"," "}), Material.BLAZE_POWDER,' ');
        }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player.getLocation().getBlockY()-player.getWorld().getHighestBlockYAt(player.getLocation())<20){

        }
        else{

        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
