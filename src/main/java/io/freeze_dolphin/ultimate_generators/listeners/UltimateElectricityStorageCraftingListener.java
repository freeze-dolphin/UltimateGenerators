package io.freeze_dolphin.ultimate_generators.listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
				ItemStack lambda8 = UGItems.LAMBDA_ELECTRICITY_STORAGE.clone();
				lambda8.setAmount(8);
				if (lambda8.equals(ei.getItemStack())) {
					// e.setCancelled(true);
					Location el = ei.getLocation();
					el.setY(el.getY() + 1);
					ei.remove();

					el.getWorld().playSound(el, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1F, 1F);
					Item dit = el.getWorld().dropItem(el, UGItems.KAPA_ELECTRICITY_STORAGE);
					el.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 3);
					dit.setGravity(false);
					dit.setInvulnerable(true);
					dit.setCustomNameVisible(true);
					dit.setCustomName(dit.getItemStack().getItemMeta().getDisplayName());
				}
			}
		}
	}

	@EventHandler
	public void ede(EntityDamageEvent e) {
		if (e.getCause().equals(DamageCause.LAVA)) {
			if (e.getEntity() instanceof Item) {
				Item ei = (Item) e.getEntity();
				ItemStack kapa8 = UGItems.KAPA_ELECTRICITY_STORAGE.clone();
				kapa8.setAmount(8);
				if (kapa8.equals(ei.getItemStack())) {
					e.setCancelled(true);
					Location el = ei.getLocation();
					el.setY(el.getY() + 1);
					ei.remove();

					el.getWorld().playSound(el, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1F, 1F);
					Item dit = el.getWorld().dropItem(el, UGItems.PHI_ELECTRICITY_STORAGE);
					el.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, el, 3);
					dit.setGravity(false);
					dit.setInvulnerable(true);
					dit.setCustomNameVisible(true);
					dit.setCustomName(dit.getItemStack().getItemMeta().getDisplayName());
				}
			}
		}
	}

}
