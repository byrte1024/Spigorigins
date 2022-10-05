package com.rdactive.spigorigins;

import com.rdactive.spigorigins.Origins.*;
import com.rdactive.spigorigins.Origins.Void;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Spigorigins extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("origin")).setExecutor(new OriginMenuCommand(this));
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        OriginManager.registerOrigin(new BlazeBorn());
        OriginManager.registerOrigin(new Fox());
        OriginManager.registerOrigin(new Goblin());
        OriginManager.registerOrigin(new Void());
        OriginManager.registerOrigin(new Werewolf());
    }

    @Override
    public void onDisable() {

    }
}
