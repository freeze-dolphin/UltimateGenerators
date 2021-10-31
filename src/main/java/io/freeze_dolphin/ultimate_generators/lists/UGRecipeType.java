package io.freeze_dolphin.ultimate_generators.lists;

import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import org.bukkit.Material;

public class UGRecipeType {

    public static RecipeType NULL = new RecipeType(new CustomItem(new UniversalMaterial(Material.BARRIER), "&c不可合成&r"));

}
