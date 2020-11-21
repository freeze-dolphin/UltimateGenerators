package io.github.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import io.github.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

public class UGItems {


	public static ItemStack ENDLESS_GENERATOR;

	public static ItemStack NETHER_STAR_GENERATOR;


	public static ItemStack DIESEL_REFINERY;
	public static ItemStack DIESEL_BUCKET;
	public static ItemStack DIESEL_GENERATOR;


	public static ItemStack BIOMASS_EXTRACTION_MACHINE;
	public static ItemStack BIOMASS_BUCKET;
	public static ItemStack BIOFUEL_REFINERY;
	public static ItemStack BIOFUEL_BUCKET;
	public static ItemStack BIOFUEL_GENERATOR;

	public static ItemStack DRAGON_BREATH_GENERATOR;

	public static ItemStack REACTION_GENERATOR;

	public static ItemStack ENDER_CRYSTAL_GENERATOR;
	public static ItemStack ENDER_CRYSTAL_GENERATOR_BASE;
	public static ItemStack ENDER_CYRSTAL_STABILIZER;
	
	public static ItemStack ENDERIUM_EXTRACTOR;
	public static ItemStack ENDERIUM_DUST, ENDERIUM_INGOT;
	
	public static ItemStack MODULAR_GENERATOR_REGULATOR;

	static {
		try {
			ENDLESS_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5ZjM1NmY1ZmU3ZDFiYzkyY2RkZmFlYmEzZWU3NzNhYzlkZjFjYzRkMWMyZjhmZTVmNDcwMTMwMzJjNTUxZCJ9fX0="),"&6无尽发电机&r", "", "&r当接收到红石信号时工作", "&5创造发电机", "&8⇨ &e⚡ &715360 J/s");

			NETHER_STAR_GENERATOR = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 7), "&d下届之星发电机&r", "", "&4终极发电机", "&8⇨ &e⚡ &71024 J 缓存", "&8⇨ &e⚡ &7128 J/s");

			DIESEL_REFINERY = new CustomItem(new UniversalMaterial(Material.PISTON_BASE), "&c柴油精炼器&r", "", "&r精炼石油生产柴油");
			DIESEL_BUCKET = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFmZTgzNjkyZjcxZjA1ZDExM2Q4OTFmOGI4MzhlMDdkN2NiODE0MTliMjBkYzFiMjA5ZWI4NGY3NzFmMGM5ZiJ9fX0="),"&r桶装柴油&r");
			DIESEL_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWVlMTE1NjU2YmMyMjA3NTVjNmQ1YmM4NzI2MGM4MjE0MWMyNTNhMzRiYzNkYjJiZDcyYjcyN2JlZmNjMCJ9fX0="),"&7柴油发电机&r", "", "&6高级发电机", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &736 J/s");

			BIOMASS_EXTRACTION_MACHINE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 5), "&a生物质萃取机&r", "", "&a中级机器", "&8⇨ &e⚡ &76 J/s");
			BIOFUEL_REFINERY = new CustomItem(new UniversalMaterial(Material.PISTON_BASE), "&c生物燃油精炼器&r", "", "&r精炼生物质生产生物燃油");
			BIOMASS_BUCKET = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTdjNDA3YWE3YjMzNjIyNTMwOWQ1MzUwZTNmNDUyYmZjZjM1NTY5MmU5ODFkODFkZGZlMzIwOGJjZjZlZDI2YyJ9fX0="),"&r桶装生物燃油&r");
			BIOFUEL_BUCKET = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFmZTgzNjkyZjcxZjA1ZDExM2Q4OTFmOGI4MzhlMDdkN2NiODE0MTliMjBkYzFiMjA5ZWI4NGY3NzFmMGM5ZiJ9fX0="),"&r桶装柴油&r");
			BIOFUEL_GENERATOR = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 4), "&2生物燃油发电机&r", "", "&6高级发电机", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &736 J/s");

			DRAGON_BREATH_GENERATOR = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 2), "&5龙息发电机&r", "", "&6高级发电机", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &718 J/s");

			REACTION_GENERATOR = new CustomItem(new UniversalMaterial(Material.STAINED_CLAY, 13), "&2反应发电机&r", "", "&r无需冷却的小型核反应发电机", "&r直接消耗低能核燃料发电", "&r但此发电方式的燃料利用率较低", "", "&6高级发电机", "&8⇨ &e⚡ &7256 J 缓存", "&8⇨ &e⚡ &716 J/s");

			ENDER_CRYSTAL_GENERATOR = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 10), "&d末影水晶发电机&r", "", "&r连接并利用附近的稳定化末影水晶发电", "", "&4终极发电机", "&8⇨ &e⚡ &714336 J 缓存", "&8⇨ &e⚡ &70 ~ +∞ J/s");
			ENDER_CRYSTAL_GENERATOR_BASE = new CustomItem(new UniversalMaterial(Material.END_BRICKS), "&f末影水晶发电机基座&r");
			ENDER_CYRSTAL_STABILIZER = new CustomItem(new UniversalMaterial(Material.OBSIDIAN), "&5末影水晶稳定机&r", "", "&6高级机器", "&8⇨ &e⚡ &718 J/s");
			
			ENDERIUM_EXTRACTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM4ZmZlN2Q2MDdkMTBiYzk3MTJkY2IxOWU4YjVkZjFlNTlkNGQ3MWQ1N2NlOTNlYWRiYTFhYzc2NmI3MTA2ZSJ9fX0="), "&5末影之尘精炼机&r", "", "&6高级机器", "&8⇨ &e⚡ &732 J/s");
			
			ENDERIUM_DUST = new CustomItem(new UniversalMaterial(Material.SUGAR), "&5末影之尘&r");
			ENDERIUM_DUST.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			ENDERIUM_DUST.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ENDERIUM_INGOT = new CustomItem(new UniversalMaterial(Material.IRON_INGOT), "&5末影锭&r");
			ENDERIUM_INGOT.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			ENDERIUM_INGOT.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			
			MODULAR_GENERATOR_REGULATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI4NzM2ZmY0MmJjNjhjMTFlZGU4ZDg3NGIxNTEyZDI5ZjJlOGM1ZjZmZWJlOGY1OGRmY2Q5YTBhNTFkNmRlZSJ9fX0="), "&b模块化发电机核心&r");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
