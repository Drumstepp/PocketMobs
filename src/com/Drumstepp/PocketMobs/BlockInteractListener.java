package com.Drumstepp.PocketMobs;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockInteractListener implements Listener {
	
	public void onPlayerBlockInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block e = event.getClickedBlock();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (itemInHand.getType().name() != "GLASS_BOTTLE" && !itemInHand.getItemMeta().hasLore()) {
			return;
		}
		
	}

}
