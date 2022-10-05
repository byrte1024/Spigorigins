package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class Goblin extends Origin {
    public Goblin(){
        super("Goblin","GOBLIN","these green creatures live in the mountains, they may be small. but are swift. and strong", Arrays.asList(new String[]{"50% faster","25% stronger"}), Arrays.asList(new String[]{"2 less hearts","can only eat uncooked meat"}), Material.GREEN_DYE,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.15);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1.25);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);
    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
