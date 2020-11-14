package io.github.freeze_dolphin.ultimate_generators;

import org.bukkit.inventory.ItemStack;

public class Utils {
		
	public static ItemStack[] buildRecipe(ItemStack ... itemStacks) {
		ItemStack[] itArr = new ItemStack[] {null, null, null, null, null, null, null, null, null};
		if (itemStacks.length > 9) return itArr;
		for (int i = 0; i < itArr.length; i++) {
			itArr[i] = itemStacks[i];
		}
		return itArr;
	}
	
}
