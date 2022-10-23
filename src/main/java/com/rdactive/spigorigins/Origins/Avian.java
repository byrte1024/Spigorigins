package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Avian extends Origin {

    public Avian() {
        super("Avian","AVIAN","A flying winged creature that lives in the clouds",Arrays.asList("Permanent slow falling","10% faster"), Collections.singletonList("Cant eat meat"), Material.FEATHER,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        if(player==null){
            return;
        }
        if(!player.isSneaking()){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,20,1));
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.11);
        }

    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
