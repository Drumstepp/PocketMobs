package com.Drumstepp.PocketMobs;

import java.util.ArrayList;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class BlockInteractListener implements Listener {
	private static ArrayList<String> cancelledPlayers = new ArrayList<String>();
	private final Plugin plugin;
	
	
	
	public BlockInteractListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerBlockInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block b = event.getClickedBlock();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (itemInHand.getType().toString() != "GLASS_BOTTLE") {
			return;
		} 
		if (event.getHand() != null && !event.getHand().equals(EquipmentSlot.HAND)) {
			return;
		}
		if (cancelledPlayers.contains(p.getUniqueId().toString())) {
			event.setCancelled(true);
			cancelledPlayers.remove(p.getUniqueId().toString());
			return;
		}
		else if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasLore() && b != null){
			ItemMeta meta = itemInHand.getItemMeta();
			NamespacedKey key = new NamespacedKey(PocketMobs.pluginInstance, "entity_type");
			String entityType = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
			
			p.getWorld().spawnEntity(b.getLocation().add(0, 1, 0), EntityType.valueOf(entityType), false);
			
			int stackSize = itemInHand.getAmount();
			if (stackSize > 1) {
				itemInHand.setAmount(stackSize - 1);
			}
			else {
				p.getInventory().remove(itemInHand);
			}
			
		}
		
	}
	
	public static void addCancelledPlayer(Player p) {
		cancelledPlayers.add(p.getUniqueId().toString());
	}

}
