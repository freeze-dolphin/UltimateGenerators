package io.freeze_dolphin.ultimate_generators.listeners;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.lists.UGItems;

public class UltimateElectricityStorageCraftingListener implements Listener {

	@EventHandler
	public void afe(PlayerFishEvent e) {
		if (e.isCancelled()) return;
		if (e.getState().equals(State.CAUGHT_ENTITY)) {
			if (e.getCaught() instanceof Item) {
				Item ei = (Item) e.getCaught();
				ItemStack lambda8 = UGItems.LAMBDA_ELECTRICITY_STORAGE;
				lambda8.setAmount(8);
				if (ei.getItemStack().equals(ei.getItemStack())) {
					e.setCancelled(true);
					Location el = ei.getLocation();
					el.setY(el.getY() + 1);
					ei.remove();
					
					el.getWorld().playSound(el, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1F, 1F);
					ArmorStand as = (ArmorStand) el.getWorld().spawnEntity(el, EntityType.ARMOR_STAND);
					as.setMaximumNoDamageTicks(6000);
					as.setNoDamageTicks(6000);
					as.setGlowing(true);
					as.setArms(true);
					as.setItemInHand(UGItems.KAPA_ELECTRICITY_STORAGE);
				}
			}
		}
	}
	
	@EventHandler
	public void ede(EntityDamageEvent e) {
		if (e.getCause().equals(DamageCause.LAVA)) {
			if (e.getEntity() instanceof Item) {
				Item ei = (Item) e.getEntity();
				ItemStack lambda8 = UGItems.KAPA_ELECTRICITY_STORAGE;
				lambda8.setAmount(8);
				if (ei.getItemStack().equals(ei.getItemStack())) {
					e.setCancelled(true);
					Location el = ei.getLocation();
					el.setY(el.getY() + 1);
					ei.remove();
					
					el.getWorld().playSound(el, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1F, 1F);
					ArmorStand as = (ArmorStand) el.getWorld().spawnEntity(el, EntityType.ARMOR_STAND);
					as.setMaximumNoDamageTicks(6000);
					as.setNoDamageTicks(6000);
					as.setGlowing(true);
					as.setArms(true);
					as.setItemInHand(UGItems.PHI_ELECTRICITY_STORAGE);
				}
			}
		}
	}

}
