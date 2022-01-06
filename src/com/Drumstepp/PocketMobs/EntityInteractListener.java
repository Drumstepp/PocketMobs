package com.Drumstepp.PocketMobs;
import java.util.Arrays;
import java.util.StringJoiner;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class EntityInteractListener implements Listener {
	private final Plugin plugin;
	
	public EntityInteractListener(Plugin plugin) {
		this.plugin = plugin;
	}


	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteractAtEntityEvent(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();
		ItemStack itemInHand = p.getInventory().getItemInMainHand();
		if (itemInHand.getType().name() != "GLASS_BOTTLE") {
			return;
		}
		if (event.getHand() != null && !event.getHand().equals(EquipmentSlot.HAND)) {
			return;
		}
		// define types of entities that are allowed to be picked up. TODO: make this configurable
		EntityType[] allowedEntityTypes = new EntityType[] 
				{
						EntityType.ZOMBIE, 
						EntityType.VILLAGER, 
						EntityType.ZOMBIE_VILLAGER, 
						EntityType.SKELETON, 
						EntityType.CREEPER,
						EntityType.BLAZE,
						EntityType.CHICKEN,
						EntityType.COW,
						EntityType.SHEEP,
						EntityType.CAT,
						EntityType.AXOLOTL,
						EntityType.CAVE_SPIDER,
						EntityType.PIG,
						EntityType.PIGLIN,
						EntityType.ZOMBIFIED_PIGLIN,
						EntityType.HOGLIN,
						EntityType.PIGLIN_BRUTE,
						EntityType.POLAR_BEAR,
						EntityType.ENDERMAN,
						EntityType.SHULKER,
						EntityType.PHANTOM,
						EntityType.SILVERFISH,
						EntityType.BAT,
						EntityType.BEE,
						EntityType.DONKEY,
						EntityType.DOLPHIN,
						EntityType.DROWNED,
						EntityType.IRON_GOLEM,
						EntityType.PHANTOM,
						EntityType.MAGMA_CUBE,
						EntityType.SLIME,
						EntityType.LLAMA,
						EntityType.TRADER_LLAMA,
						EntityType.TURTLE,
						EntityType.PILLAGER,
						EntityType.WOLF,
						EntityType.GLOW_SQUID,
						EntityType.SQUID,
						EntityType.WANDERING_TRADER,
						EntityType.WITCH,
						EntityType.SNOWMAN,
						EntityType.OCELOT,
						EntityType.PANDA,
						EntityType.FOX,
						EntityType.STRIDER
						};
		

		EntityType entityType = e.getType();
		var allowedEntityTypesList = Arrays.asList(allowedEntityTypes);
		
		// if clicked entity is one of the allowed ones and glass bottle does not have "lore" set. 
		if (allowedEntityTypesList.contains(entityType) && !itemInHand.getItemMeta().hasLore()) {
			if (entityType == EntityType.VILLAGER) {
				
				Villager v = (Villager) e;

				if (v.getProfession() != Profession.NONE) {
					p.sendMessage(ChatColor.RED + "You may not pick up a villager with a profession.");
					event.setCancelled(true);
					return; // do not allow villagers with a profession to be moved.
				}
				
			}
			if (e instanceof Animals) {
				Animals a = (Animals) e;
				if (!a.isAdult()) {
					p.sendMessage(ChatColor.RED + "Don't try to pick up baby's, weirdo.");
					event.setCancelled(true);
					return;
				}
			}
			
			BlockInteractListener.addCancelledPlayer(p); // make sure the other listener cancels it's event.
		
			e.remove(); // delete clicked mob
			
			int stackSize = itemInHand.getAmount();
			if (stackSize > 1) {
				itemInHand.setAmount(stackSize - 1);
			}
			else {
				p.getInventory().remove(itemInHand);
			}
			
			
			ItemStack newBottle = new ItemStack(Material.GLASS_BOTTLE);
			ItemMeta itemMeta = newBottle.getItemMeta();
			String entityFriendlyName = toTitleCase(e.getType().name());
			itemMeta.setDisplayName(ChatColor.RED + entityFriendlyName + " in a bottle");
			itemMeta.setLore(Arrays.asList("Through some kind of dark magic, you have managed to trap a " + entityFriendlyName + " in this bottle."));
			NamespacedKey key = new NamespacedKey(PocketMobs.pluginInstance, "entity_type");
			itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, e.getType().toString());
			
			newBottle.setItemMeta(itemMeta);
			
			p.getInventory().addItem(newBottle);
			p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1.0f, 6.0f);

			
				
		}
		
	}
	
	private String toTitleCase(String s) {
		String[] stringArray = s.split("_");
		StringJoiner returnString = new StringJoiner(" ");
		for (int i = 0; i < stringArray.length; i++) {
			String str = stringArray[i];
			returnString.add(str.substring(0, 1) + str.substring(1, str.length()).toLowerCase());
		}
		return returnString.toString();
	}
	
}
