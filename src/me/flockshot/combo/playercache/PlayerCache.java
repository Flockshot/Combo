package me.flockshot.combo.playercache;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerCache
{
    private UUID uuid;
    private Player victim = null;
    private Player attacker = null;
    
    public PlayerCache(UUID uuid) {
        setUUID(uuid);
    }
    
    public UUID getUuid() {
        return uuid;
    }    
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
    
    public Player getVictim() {
        return victim;
    }
    public void setVictim(Player victim) {
        this.victim = victim;
    }

    public Player getAttacker() {
        return attacker;
    }

    public void setAttacker(Player attacker) {
        this.attacker = attacker;
    }
    
    
}
