package io.freeze_dolphin.ultimate_generators.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;

public class EnderCrystalEnhancerListener implements Listener {

	@EventHandler
	public void piaee(PlayerInteractAtEntityEvent e) {
		if (e.isCancelled()) return;
		if (e.getRightClicked() == null) return;
		if (e.getRightClicked() instanceof EnderCrystal) {
			EnderCrystal ec = (EnderCrystal) e.getRightClicked();

			Player p = e.getPlayer();
			if (SlimefunManager.isItemSimiliar(p.getInventory().getItemInMainHand(), UGItems.ENDER_CRYSTAL_ENHANCER, true)) {
				if (!ec.isShowingBottom() && (ec.getCustomName() == null || ec.getCustomName().equals(""))) {
					p.getInventory().setItemInMainHand(InvUtils.decreaseItem(p.getInventory().getItemInMainHand(), 1));
					ec.setCustomName(ChatColor.translateAlternateColorCodes('&', "&d&l强化末影水晶&r"));
					ec.setCustomNameVisible(false);
				}
			}
		}
	}

}
