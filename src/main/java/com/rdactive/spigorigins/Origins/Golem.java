package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class Golem extends Origin {

    public Golem() {
        super("Golem","GOLEM","A large impenetrable creature made of stone", Collections.singletonList("Permanent resistance I"), Collections.singletonList("Permanent slowness I"), Material.IRON_BLOCK,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player==null){
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20,0));
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
