package com.rdactive.spigorigins;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Spigorigins extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("origin")).setExecutor(new OriginMenuCommand(this));
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
