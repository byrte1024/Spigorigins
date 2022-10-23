package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;

public class Frog extends Origin {
    public Frog() {
        super("Frog","FROG","The high jumping creatures of the swamp!", Arrays.asList("Jumps 2x higher","is not slowed down in water"), Collections.singletonList("Has permanent slowness I"), Material.FEATHER,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player==null){
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,20,1));
        if(player.getLocation().getBlock().getType()!=Material.WATER){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,0));
        }
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
