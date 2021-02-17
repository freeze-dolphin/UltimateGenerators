package io.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;

import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Objects.Category;

public class UGCategories {

    public static Category SLIMEFUN_MACHINES = Categories.ELECTRICITY;
    public static Category TECH_MISC = Categories.TECH_MISC;

    public static Category MACHINES = new Category(new CustomItem(new UniversalMaterial(Material.PAPER),
            "&d终极发电机 &7- &9机器&r", "", "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"), 4);
    public static Category ELECTRICITY_STORAGE = new Category(
            new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 8), "&d终极发电机 &7- &e电力储存&r", "",
                    "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"),
            4);

    public static Category SINGLE_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.TRIPWIRE_HOOK),
            "&d终极发电机 &7- &b简易发电&r", "", "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"), 4);
    public static Category MODULAR_GENERATOR = new Category(
            new CustomItem(new UniversalMaterial(Material.BLACK_SHULKER_BOX), "&d终极发电机 &7- &c模块发电&r", "",
                    "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"),
            5);

    public UGCategories(Loader plug) {
        if (plug.getProperties().contains("hide-author-name-in-category")
                && plug.getProperties().get("hide-author-name-in-category").equals("true")) {
            MACHINES = new Category(
                    new CustomItem(new UniversalMaterial(Material.PAPER), "&d终极发电机 &7- &9机器&r", "", "&a> 点击打开"), 4);
            SINGLE_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.TRIPWIRE_HOOK),
                    "&d终极发电机 &7- &b简易发电&r", "", "&a> 点击打开"), 4);
            ELECTRICITY_STORAGE = new Category(new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 8),
                    "&d终极发电机 &7- &e电力储存&r", "", "&a> 点击打开"), 4);
            MODULAR_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.BLACK_SHULKER_BOX),
                    "&d终极发电机 &7- &c模块发电&r", "", "&a> 点击打开"), 5);
        }
    }

}
