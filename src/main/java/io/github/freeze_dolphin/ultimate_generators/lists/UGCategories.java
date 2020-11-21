package io.github.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;

import io.github.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Objects.Category;

public class UGCategories {

	public static Category MACHINES = Categories.ELECTRICITY;
	
	public static Category SINGLE_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.END_CRYSTAL), "&d终极发电机 - 单体发电&r", "", "&7&oAuthor: Freeze_Dolphin", "", "&a > 点击打开"), 4);
	public static Category MODULAR_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.END_CRYSTAL), "&d终极发电机 - 模块化发电&r", "", "&7&oAuthor: Freeze_Dolphin", "", "&a > 点击打开"), 5);
	
}
