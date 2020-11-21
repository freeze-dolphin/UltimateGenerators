package io.github.freeze_dolphin.ultimate_generators.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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

	}

	private static boolean insertedCrystalAlready(Block b) {
		return (BlockStorage.hasBlockInfo(b.getLocation()) && BlockStorage.getLocationInfo(b.getLocation(), "crystal") != null && BlockStorage.getLocationInfo(b.getLocation(), "crystal").equals("true"));
	}

}
