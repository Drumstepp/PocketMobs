package com.Drumstepp.PocketMobs;

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

public class BlockInteractListener implements Listener {
	public static boolean isCancelled = false;
	
	@EventHandler
	public void onPlayerBlockInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block b = event.getClickedBlock();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		System.out.println(itemInHand.getType().toString());
		System.out.println("Block: " + event.getHand());
		if (itemInHand.getType().toString() != "GLASS_BOTTLE") {
			return;
		} 
		if (event.getHand() != null && !event.getHand().equals(EquipmentSlot.HAND)) {
			return;
		}
		if (isCancelled) {
			event.setCancelled(true);
			isCancelled = false;
			return;
		}
		else if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasLore() && b != null){
			ItemMeta meta = itemInHand.getItemMeta();
			NamespacedKey key = new NamespacedKey(PocketMobs.pluginInstance, "entity_type");
			String entityType = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
			
			p.getWorld().spawnEntity(b.getLocation().add(0, 1, 0), EntityType.valueOf(entityType));
			System.out.println("Spawning: " + entityType);
			p.getInventory().remove(itemInHand);
			
		}
		
		
		
	}

}
