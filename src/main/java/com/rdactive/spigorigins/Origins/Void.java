package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Void extends Origin {
    public Void(){
        super("V.O.I.D","VOID","These creatures are a scientific experiment made by [REDACTED] for [CENSORSHIP] because of [TOP SECRET]", Arrays.asList(new String[]{"50% faster","50% stronger","5 more hearts","Death by void wont kill you"}), Arrays.asList(new String[]{"Permanent blindness"}), Material.BLACK_CONCRETE_POWDER,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.15);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1.5);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,80,1));
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
