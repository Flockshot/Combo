package me.flockshot.combo.executable;

import org.bukkit.entity.Player;

import me.flockshot.combo.ComboPlugin;

public abstract class UnknownExecutable implements Executable
{
    private String value;
    private Player playerToExecute;
    protected ComboPlugin plugin;
    
    protected UnknownExecutable() {
        plugin = ComboPlugin.getInstance();
    }

    @Override
    public String getValue() {
        return value;
    }
    @Override
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public Player getPlayerToExecute() {
        return playerToExecute;
    }
    
    @Override
    public void setPlayerToExecute(Player p)
    {
        Player attacker = plugin.getPlayerCacheManager().getPlayerCache(p).getAttacker();
        Player victim = plugin.getPlayerCacheManager().getPlayerCache(p).getVictim();
        
        switch(value.split(" ")[0])
        {
            case "%victim%":
                playerToExecute = victim;
                break;
            case "%attacker%":
                playerToExecute = attacker;
                break;
            default:
                playerToExecute = p;
                break;
        }
        
        value = value.replaceAll("%self%", p.getName());
        value = value.replaceAll("%victim%", victim!=null ? victim.getName() : "");        
        value = value.replaceAll("%attacker%", attacker!=null ? attacker.getName() : "");        
    }
    

    

    final public void execute(Player player)
    {
        setPlayerToExecute(player);
        execute();
    }
   
    @Override
    public boolean passesValidity(String value) {
        return true;
    }

}
