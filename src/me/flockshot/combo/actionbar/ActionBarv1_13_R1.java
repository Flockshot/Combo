package me.flockshot.combo.actionbar;

import org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R1.PacketPlayOutChat;
import net.minecraft.server.v1_13_R1.IChatBaseComponent.ChatSerializer;


public class ActionBarv1_13_R1 implements ActionBar
{
	@Override
	public void sendActionBar(Player player, String text)
	{
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);		
	}
}
