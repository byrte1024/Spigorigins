package com.rdactive.spigorigins.Origins;

import com.rdactive.spigorigins.Asigner;
import com.rdactive.spigorigins.Origin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Human extends Origin {
    public Human(){
        super(
                "Human"
                ,"HUMAN"
                ,"Just your regular ol' human"
                ,Arrays.asList("No Advantages")
                ,Arrays.asList("No Disadvantages")
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
