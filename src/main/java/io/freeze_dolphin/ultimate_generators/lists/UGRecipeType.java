package io.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class UGRecipeType {

    public static RecipeType NULL = new RecipeType(new CustomItem(Material.BARRIER, "&c不可合成&r"), null);

    public UGRecipeType() {
    }

}
