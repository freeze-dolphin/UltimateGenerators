package io.github.freeze_dolphin.ultimate_generators;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;

public class Utils {

	public static ItemStack[] buildRecipe(ItemStack ... itemStacks) {
		ItemStack[] itArr = new ItemStack[] {null, null, null, null, null, null, null, null, null};
		if (itemStacks.length <= 0) return itArr;
		if (itemStacks.length > 9) return itArr;
		for (int i = 0; i < itArr.length; i++) {
			itArr[i] = itemStacks[i];
		}
		return itArr;
	}

	public static boolean reflectCanOpenMethod(Block b, Player p) {
		try {
			Class.forName("me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils");
			boolean perm = (p.hasPermission("slimefun.inventory.bypass")) || (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
			return (perm) && (ProtectionUtils.canAccessItem(p, b));
		} catch (ClassNotFoundException cnfe) {
			return p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true);
		}
	}

	public static void setMetadata(Metadatable object, String key, Object value, Plugin plugin) {
		object.setMetadata(key, new FixedMetadataValue(plugin,value));
	}

	public static Object getMetadata(Metadatable object, String key, Plugin plugin) {
		List<MetadataValue> values = object.getMetadata(key);  
		for (MetadataValue value : values) {
			if (value.getOwningPlugin() == plugin) {
				return value.value();
			}
		}
		return null;
	}

}
