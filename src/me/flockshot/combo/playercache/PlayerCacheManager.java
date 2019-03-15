package me.flockshot.combo.playercache;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerCacheManager
{
    
    HashMap<UUID, PlayerCache> cache = new HashMap<>();
    
    public void addPlayer(Player player)
    {
        cache.putIfAbsent(player.getUniqueId(), new PlayerCache(player.getUniqueId()));
    }
    
    public void removePlayer(Player player)
    {
        cache.remove(player.getUniqueId());
    }
    
    public PlayerCache getPlayerCache(Player player)
    {
        return cache.get(player.getUniqueId());
    }
    
}
