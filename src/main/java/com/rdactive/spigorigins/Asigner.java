package com.rdactive.spigorigins;

import org.bukkit.entity.Player;

public class Asigner {
    protected String playerName;
    protected String origin;

    public String getOrigin() {
        return origin;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPlayer(String playerName) {
        this.playerName = playerName;
    }
    public void setPlayer(Player player) {
        this.playerName = player.getName();
    }

    public Asigner(String playerName){
        this.playerName=playerName;
    }
}
