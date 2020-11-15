package io.github.freeze_dolphin.ultimate_generators;

import org.bukkit.Material;

import io.github.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Objects.Category;

public class GlobalVariables {

	public static Category c = new Category(new CustomItem(new UniversalMaterial(Material.END_CRYSTAL), "&d终极发电机&r", "", "&7&oAuthor: Freeze_Dolphin", "", "&a > 点击打开"), 5);

}
