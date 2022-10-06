package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;

public class Human extends Origin {
    public Human(){
        super(
                "Human"
                ,"HUMAN"
                ,"Just your regular ol' human"
                , Collections.singletonList("No Advantages")
                , Collections.singletonList("No Disadvantages")
                , Material.GRASS_BLOCK
                ,' '
        );

    }

    @Override
    public void applyEffects(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginSelect(Player player, Asigner asigner) {

    }

    @Override
    public void onOriginLeave(Player player, Asigner asigner) {

    }
}
