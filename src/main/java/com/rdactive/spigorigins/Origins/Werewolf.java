package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class Werewolf extends Origin {
    public Werewolf(){
        super("WereWolf","WEREWOLF","these foxes are fairly week. but when the moon shines. they are at their strongest", Arrays.asList(new String[]{"25% faster at night","4 hearts more at night","25% stronger at night"}), Arrays.asList(new String[]{"25% slower at day","4 hearts less at day","25% weaker at day"}), Material.BONE,' ');
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
