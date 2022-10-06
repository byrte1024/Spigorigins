package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;

public class BlazeBorn extends Origin {
    public BlazeBorn(){
        super("BlazeBorn","BLAZE","The flaming creatures of the nether.", Arrays.asList("Does not burn","Cannot be poisoned"), Collections.singletonList("Takes damage in water"), Material.BLAZE_POWDER,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player.getFireTicks()>0){
            player.setFireTicks(0);
        }
        if(player.hasPotionEffect(PotionEffectType.POISON)){
            player.removePotionEffect(PotionEffectType.POISON);
        }
        if(player.getLocation().getBlock().getType()==Material.WATER){
            player.damage(2);
        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
