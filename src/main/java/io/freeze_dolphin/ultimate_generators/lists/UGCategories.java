package io.freeze_dolphin.ultimate_generators.lists;

import java.io.InputStreamReader;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Objects.Category;

public class UGCategories {

	public static Category SLIMEFUN_MACHINES = Categories.ELECTRICITY;
	public static Category TECH_MISC = Categories.TECH_MISC;

	public static Category SINGLE_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.TRIPWIRE_HOOK), "&d终极发电机 &7- &b简易发电&r", "", "&7&oAuthor: Freeze_Dolphin", "&a > 点击打开"), 4);
	public static Category ELECTRICITY_STORAGE = new Category(new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 8), "&d终极发电机 &7- &e电力储存&r", "", "&7&oAuthor: Freeze_Dolphin", "&a > 点击打开"), 4);

	public UGCategories(Loader plug) {
		YamlConfiguration pdf = new YamlConfiguration();

		try {
			pdf.load(new InputStreamReader(plug.getClass().getClassLoader().getResourceAsStream("plugin.yml")));
		} catch (Exception e) {
			e.printStackTrace();
			plug.getLogger().severe("The internal plugin description file 'plugin.yml' cannot be read, self-disabling...");
			plug.getServer().getPluginManager().disablePlugin(plug);
		}

		if (pdf.contains("hide-author-name-in-category") && pdf.isBoolean("hide-author-name-in-category") && pdf.getBoolean("hide-author-name-in-category")) {

			SINGLE_GENERATOR = new Category(new CustomItem(new UniversalMaterial(Material.TRIPWIRE_HOOK), "&d终极发电机 &7- &b简易发电&r", "", "&a > 点击打开"), 4);
			ELECTRICITY_STORAGE = new Category(new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 8), "&d终极发电机 &7- &e电力储存&r", "", "&a > 点击打开"), 4);

		}
	}

}
