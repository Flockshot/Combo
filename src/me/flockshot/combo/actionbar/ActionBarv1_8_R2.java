package me.flockshot.combo.actionbar;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R2.PacketPlayOutChat;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;


public class ActionBarv1_8_R2 implements ActionBar
{
	@Override
	public void sendActionBar(Player player, String text)
	{
		// TODO Auto-generated method stub
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + text + "\"}"), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
		
	}
}
