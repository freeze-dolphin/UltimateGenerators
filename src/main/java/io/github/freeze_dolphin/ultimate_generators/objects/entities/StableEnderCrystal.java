package io.github.freeze_dolphin.ultimate_generators.objects.entities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import io.github.freeze_dolphin.ultimate_generators.Loader;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class StableEnderCrystal {

	public static EnderCrystal getCrystal(Location stabilizer) {
		Location l = new Location(stabilizer.getWorld(), stabilizer.getX() + 0.5D, stabilizer.getY(), stabilizer.getZ() + 0.5D);
		for (Entity n : l.getChunk().getEntities()) {
			if (((n instanceof EnderCrystal)) && (l.distanceSquared(n.getLocation()) < 0.4D)) {
				return (EnderCrystal) n;
			}
		}
		EnderCrystal crystal = (EnderCrystal) stabilizer.getWorld().spawnEntity(l, EntityType.ENDER_CRYSTAL);
		crystal.setCustomNameVisible(false);
		crystal.setCustomName(null);
		return crystal;
	}

	public static void update(Location l, String name) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Loader.getImplement(), new Runnable() {

			public void run() {
				EnderCrystal crystal = StableEnderCrystal.getCrystal(l);
				if (!crystal.isCustomNameVisible()) {
					crystal.setCustomNameVisible(true);
				}
				crystal.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
			}
		});
	}
	
	public static Block getStabilizer(EnderCrystal crystal) {
		Block b = crystal.getLocation().getBlock();
		if (BlockStorage.check(b, "ENDER_CYRSTAL_STABILIZER")) {
			return b;
		}
		return null;
	}
	
	public static void remove(Location l) {
		EnderCrystal crystal = getCrystal(l);
		crystal.remove();
	}

}
