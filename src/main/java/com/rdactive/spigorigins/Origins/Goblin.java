package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class Goblin extends Origin {
    public Goblin(){
        super("Goblin","GOBLIN","these green creatures live in the mountains, they may be small. but are swift. and strong", Arrays.asList("50% faster","25% stronger"), Arrays.asList("2 less hearts","can only eat uncooked meat"), Material.GREEN_DYE,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.15);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1.25);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(16);
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
