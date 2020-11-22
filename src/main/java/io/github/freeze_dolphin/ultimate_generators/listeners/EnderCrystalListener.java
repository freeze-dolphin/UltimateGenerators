package io.github.freeze_dolphin.ultimate_generators.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.freeze_dolphin.ultimate_generators.objects.entities.StableEnderCrystal;
import io.github.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator.EnderCrystalStabilizer;
import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8.ParticleEffect;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class EnderCrystalListener implements Listener {

	@EventHandler
	public void onPlace(PlayerInteractEvent e) {
		if (e.hasBlock() && e.hasItem()) {
			Block b = e.getClickedBlock();
			ItemStack i = e.getItem();

			if (BlockStorage.check(b, "ENDER_CYRSTAL_STABILIZER")) {
				e.setCancelled(true);
				if (i.getType().equals(Material.END_CRYSTAL)) {
					if (!insertedCrystalAlready(b)) {
						i.setAmount(i.getAmount() - 1);
						BlockStorage.addBlockInfo(b.getLocation(), "crystal", "true");
					}
				} else if (e.getPlayer().isSneaking() && insertedCrystalAlready(b)) {
					b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.END_CRYSTAL));
					BlockStorage.addBlockInfo(b.getLocation(), "crystal", "false");
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntityType().equals(EntityType.ENDER_CRYSTAL)) {
			EnderCrystal crystal = (EnderCrystal) e.getEntity();
			if (StableEnderCrystal.getStabilizer(crystal) != null) {
				Block b = StableEnderCrystal.getStabilizer(crystal);
				Location l = b.getLocation();
				int stability = EnderCrystalStabilizer.getStability(l);
				if (stability > 0) {
					e.setCancelled(true);
					EnderCrystalStabilizer.setStability(l, stability - 1);
					try {
						ParticleEffect.END_ROD.display(crystal.getLocation(), 0F, 0.5F, 0F, 0.1F, 5);
					} catch (Exception pe) {}
				} else {
					// Explosion!
					StableEnderCrystal.remove(l);
					BlockStorage.addBlockInfo(b.getLocation(), "crystal", "false");
				}
			}
		}
	}
	
	private static boolean insertedCrystalAlready(Block b) {
		return EnderCrystalStabilizer.insertedCrystalAlready(b);
	}

}
