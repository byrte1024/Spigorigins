package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class BlazeBorn extends Origin {
    public BlazeBorn(){
        super("BlazeBorn","BLAZE","The flaming creatures of the nether.", Arrays.asList(new String[]{"Does not burn","Cannot be poisoned"}), Arrays.asList(new String[]{"Takes damage in water"}), Material.SWEET_BERRIES,' ');
    }

    @Override
    public void applyEffects(Player player, Asigner asigner, Plugin originPL) {

    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner, Plugin originPL) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner, Plugin originPL) {

    }
}
