package io.freeze_dolphin.ultimate_generators;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.bukkit.Location;
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
	
	public static final String db64s(final String base64) {
		try {
			return new String(Base64.getDecoder().decode(base64), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Loader.severe("Error occourred while decoding Base64, have you ever modified the plugin?");
			return base64;
		}
	}
	
	public static ItemStack[] buildRecipe(ItemStack ... itemStacks) {
		
		if (itemStacks.length == 9) {
			return itemStacks;
		}
		
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

	public static Location locModify(Location orig, float xOffset, float yOffset, float zOffset) {
		return new Location(orig.getWorld(), orig.getX() + xOffset, orig.getY() + yOffset, orig.getZ() + zOffset);
	}
	
}
