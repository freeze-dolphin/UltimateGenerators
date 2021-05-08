package io.freeze_dolphin.ultimate_generators.objects.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.lists.UGItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public abstract class DieselRefinery extends AContainer {

    public DieselRefinery(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(40, new ItemStack[] { SlimefunItems.OIL_BUCKET }, new ItemStack[] { UGItems.DIESEL_BUCKET });
    }

    @Override
    public String getMachineIdentifier() {
        return "DIESEL_REFINERY";
    }

    @Override
    public String getInventoryTitle() {
        return "&c柴油精炼器";
    }

}
