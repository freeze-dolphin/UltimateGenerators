package io.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;

import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

public class UGRecipeType {
	
	public static RecipeType NULL = new RecipeType(new CustomItem(new UniversalMaterial(Material.BARRIER), "&c不可合成&r"));
	
	public UGRecipeType() {}
	
}
