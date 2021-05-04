package io.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.DefaultConfig;
import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.objects.basics.ReflectedPrivateCategory;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class UGCategories {

        /*
         * ItemStack categoryItem = new CustomItem(Material.DIAMOND, "&4Addon Category",
         * "", "&a> Click to open");
         * 
         * // Give your Category a unique id. NamespacedKey categoryId = new
         * NamespacedKey(this, "addon_category"); Category category = new
         * Category(categoryId, categoryItem);
         */

        public static Category CMachine, CStorage, CEasy, CMod;

        public static Category DTechMisc, DRes, DLAMagic, DMisc, DMagic; 

        public UGCategories() throws Exception {
                ItemStack IMachine, IStorage, IEasy, IMod;
                if (Boolean.getBoolean(DefaultConfig.getConfig("hide-author-name-in-category"))) {
                        IMachine = new CustomItem(Material.PAPER, "&d终极发电机 &7- &9机器&r", "", "&a> 点击打开");
                        IStorage = new CustomItem(Material.LIGHT_GRAY_STAINED_GLASS, "&d终极发电机 &7- &e电力储存&r", "",
                                        "&a> 点击打开");
                        IEasy = new CustomItem(Material.TRIPWIRE_HOOK, "&d终极发电机 &7- &b简易发电&r", "", "&a> 点击打开");
                        IMod = new CustomItem(Material.BLACK_SHULKER_BOX, "&d终极发电机 &7- &c模块发电&r", "", "&a> 点击打开");
                } else {
                        IMachine = new CustomItem(Material.PAPER, "&d终极发电机 &7- &9机器&r", "",
                                        "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开");
                        IStorage = new CustomItem(Material.LIGHT_GRAY_STAINED_GLASS, "&d终极发电机 &7- &e电力储存&r", "",
                                        "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开");
                        IEasy = new CustomItem(Material.TRIPWIRE_HOOK, "&d终极发电机 &7- &b简易发电&r", "",
                                        "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开");
                        IMod = new CustomItem(Material.BLACK_SHULKER_BOX, "&d终极发电机 &7- &c模块发电&r", "",
                                        "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开");
                }

                NamespacedKey NMachine = new NamespacedKey(Loader.getImplement(), "ug_c_machine");
                CMachine = new Category(NMachine, IMachine);

                NamespacedKey NStorage = new NamespacedKey(Loader.getImplement(), "ug_c_storage");
                CMachine = new Category(NStorage, IStorage);

                NamespacedKey NEasy = new NamespacedKey(Loader.getImplement(), "ug_c_easy");
                CMachine = new Category(NEasy, IEasy);

                NamespacedKey NMod = new NamespacedKey(Loader.getImplement(), "ug_c_mod");
                CMachine = new Category(NMod, IMod);

                DTechMisc = new ReflectedPrivateCategory("technicalComponents").getCategory();
                DRes = new ReflectedPrivateCategory("resources").getCategory();
                DLAMagic = new ReflectedPrivateCategory("magicalResources").getCategory();
                DMagic = new ReflectedPrivateCategory("magicalGadgets").getCategory();
                DMisc = new ReflectedPrivateCategory("misc").getCategory();
        }

        /*
         * public static Category SLIMEFUN_MACHINES = Categories.ELECTRICITY; public
         * static Category TECH_MISC = Categories.TECH_MISC;
         * 
         * public static Category MACHINES = new Category(new CustomItem(new
         * UniversalMaterial(Material.PAPER), "&d终极发电机 &7- &9机器&r", "",
         * "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"), 4); public static Category
         * ELECTRICITY_STORAGE = new Category( new CustomItem(new
         * UniversalMaterial(Material.STAINED_GLASS, 8), "&d终极发电机 &7- &e电力储存&r", "",
         * "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"), 4);
         * 
         * public static Category SINGLE_GENERATOR = new Category( new CustomItem(new
         * UniversalMaterial(Material.TRIPWIRE_HOOK), "&d终极发电机 &7- &b简易发电&r", "",
         * "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"), 4); public static Category
         * MODULAR_GENERATOR = new Category( new CustomItem(new
         * UniversalMaterial(Material.BLACK_SHULKER_BOX), "&d终极发电机 &7- &c模块发电&r", "",
         * "&7&oAuthor: Freeze_Dolphin", "&a> 点击打开"), 5);
         * 
         * public UGCategories(Loader plug) { if
         * (Boolean.getBoolean(DefaultConfig.getConfig("hide-author-name-in-category")))
         * { MACHINES = new Category(new CustomItem(new
         * UniversalMaterial(Material.PAPER), "&d终极发电机 &7- &9机器&r", "", "&a> 点击打开"), 4);
         * SINGLE_GENERATOR = new Category(new CustomItem(new
         * UniversalMaterial(Material.TRIPWIRE_HOOK), "&d终极发电机 &7- &b简易发电&r", "",
         * "&a> 点击打开"), 4); ELECTRICITY_STORAGE = new Category( new CustomItem(new
         * UniversalMaterial(Material.STAINED_GLASS, 8), "&d终极发电机 &7- &e电力储存&r", "",
         * "&a> 点击打开"), 4); MODULAR_GENERATOR = new Category( new CustomItem(new
         * UniversalMaterial(Material.BLACK_SHULKER_BOX), "&d终极发电机 &7- &c模块发电&r", "",
         * "&a> 点击打开"), 5); } }
         */

}
