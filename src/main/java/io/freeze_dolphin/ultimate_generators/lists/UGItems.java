package io.freeze_dolphin.ultimate_generators.lists;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import io.freeze_dolphin.ultimate_generators.Loader;
import io.freeze_dolphin.ultimate_generators.Utils;
import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;
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

	public static ItemStack SOLID_STORAGE_EXPANSION, LIQUID_STORAGE_EXPANSION;
	public static ItemStack COAL_GENERATOR, LAVA_GENERATOR, COMBUSTION_REACTOR;
	public static ItemStack BIO_REACTOR = new CustomItem(new UniversalMaterial(Material.STAINED_CLAY, 5), "&2生化反应器 &7(扩容版)&r", "", "&6发电机组", "&8\u21E8 &e\u26A1 &7128 J 缓存", "&8\u21E8 &e\u26A1 &78 J/s");

	public static ItemStack ELECTRICITY_STORAGE_UNIT;
	public static ItemStack ADVANCED_BATTERY, ALPHA_BATTERY, BETA_BATTERY, GAMMA_BATTERY;
	public static ItemStack BASIC_ELECTRICITY_STORAGE, ADVANCED_ELECTRICITY_STORAGE, ALPHA_ELECTRICITY_STORAGE, BETA_ELECTRICITY_STORAGE, GAMMA_ELECTRICITY_STORAGE, LAMBDA_ELECTRICITY_STORAGE, KAPA_ELECTRICITY_STORAGE, PHI_ELECTRICITY_STORAGE;

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

			/*
			ENDER_CRYSTAL_GENERATOR = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 10), "&d末影水晶发电机&r", "", "&r连接并利用附近的稳定化末影水晶发电", "", "&4终极发电机", "&8⇨ &e⚡ &714336 J 缓存", "&8⇨ &e⚡ &70 ~ +∞ J/s");
			ENDER_CRYSTAL_GENERATOR_BASE = new CustomItem(new UniversalMaterial(Material.END_BRICKS), "&f末影水晶发电机基座&r");
			ENDER_CYRSTAL_STABILIZER = new CustomItem(new UniversalMaterial(Material.OBSIDIAN), "&5末影水晶稳定机&r", "", "&6高级机器", "&8⇨ &e⚡ &718 J/s/PTL");

			ENDERIUM_EXTRACTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM4ZmZlN2Q2MDdkMTBiYzk3MTJkY2IxOWU4YjVkZjFlNTlkNGQ3MWQ1N2NlOTNlYWRiYTFhYzc2NmI3MTA2ZSJ9fX0="), "&5末影之尘精炼机&r", "", "&6高级机器", "&8⇨ &e⚡ &732 J/s");

			ENDERIUM_DUST = new CustomItem(new UniversalMaterial(Material.SUGAR), "&5末影之尘&r");
			ENDERIUM_DUST.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			ENDERIUM_DUST.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			ENDERIUM_INGOT = new CustomItem(new UniversalMaterial(Material.IRON_INGOT), "&5末影锭&r");
			ENDERIUM_INGOT.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			ENDERIUM_INGOT.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			MODULAR_GENERATOR_REGULATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI4NzM2ZmY0MmJjNjhjMTFlZGU4ZDg3NGIxNTEyZDI5ZjJlOGM1ZjZmZWJlOGY1OGRmY2Q5YTBhNTFkNmRlZSJ9fX0="), "&b模块化发电机核心&r");
			 */

			SOLID_STORAGE_EXPANSION = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjRmY2M5ODlmMDMzOTQ3YmYyYjc1NDE2ZDRjZTk0YTUxOGNmN2MyYmRiNTdjNDMyNDBlODdkN2FlMjY2MzI3OSJ9fX0="), "&f固体储存拓展&r");
			LIQUID_STORAGE_EXPANSION = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZiY2QxODBiMWM4ZmM0Y2EzOWNmNDY3YzM0NWFlODNjYzE2YjYzY2IyNTU3MDM0NjAxMDNhM2VlYmE0NTcifX19"), "&f流体储存拓展&r");
			COAL_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&c煤炭发电机 &7(扩容版)&r", "", "&6发电机组", "&8\u21E8 &e\u26A1 &764 J 缓存", "&8\u21E8 &e\u26A1 &716 J/s");
			LAVA_GENERATOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&4岩浆发电机 &7(扩容版)&r", "", "&6发电机组", "&8\u21E8 &e\u26A1 &7512 J 缓存", "&8\u21E8 &e\u26A1 &720 J/s");
			COMBUSTION_REACTOR = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM0M2NlNThkYTU0Yzc5OTI0YTJjOTMzMWNmYzQxN2ZlOGNjYmJlYTliZTQ1YTdhYzg1ODYwYTZjNzMwIn19fQ=="), "&c燃烧反应器 &7(扩容版)&r", "", "&6高级发电机", "&8\u21E8 &e\u26A1 &7256 J 缓存", "&8\u21E8 &e\u26A1 &724 J/s");

			ELECTRICITY_STORAGE_UNIT = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7ImlkIjoiODljMzVjZjNmZmZkNDAxMWJhMTM5MmJiYjE1MjM1NzYiLCJ0eXBlIjoiU0tJTiIsInVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFhM2NkOWIwMTZiMTIyOGVjMDFmZDZmMDk5MmM2NGYzYjliN2IyOTc3M2ZhNDY0MzlhYjNmM2M4YTM0NzcwNCIsInByb2ZpbGVJZCI6IjYwNTc2YTMwMzYwYjRhYTBiYTRjNTczMGUyZDhhYTc0IiwidGV4dHVyZUlkIjoiMzFhM2NkOWIwMTZiMTIyOGVjMDFmZDZmMDk5MmM2NGYzYjliN2IyOTc3M2ZhNDY0MzlhYjNmM2M4YTM0NzcwNCJ9fSwic2tpbiI6eyJpZCI6Ijg5YzM1Y2YzZmZmZDQwMTFiYTEzOTJiYmIxNTIzNTc2IiwidHlwZSI6IlNLSU4iLCJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMxYTNjZDliMDE2YjEyMjhlYzAxZmQ2ZjA5OTJjNjRmM2I5YjdiMjk3NzNmYTQ2NDM5YWIzZjNjOGEzNDc3MDQiLCJwcm9maWxlSWQiOiI2MDU3NmEzMDM2MGI0YWEwYmE0YzU3MzBlMmQ4YWE3NCIsInRleHR1cmVJZCI6IjMxYTNjZDliMDE2YjEyMjhlYzAxZmQ2ZjA5OTJjNjRmM2I5YjdiMjk3NzNmYTQ2NDM5YWIzZjNjOGEzNDc3MDQifSwiY2FwZSI6bnVsbH0="), "&f电力储存单元&r");
			ADVANCED_BATTERY = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRmMjFjZjVjMjM0ZmM5NmRiOTBhMGEzMTFkNmZiZTEyZjg3ODliN2ZhODE1NTcxNjc1N2ZkNTE2YjE4MTEifX19"), "&e高级电池&r");
			ALPHA_BATTERY = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODUzYjRjYTdmZTlhOWUyOTlkMzc3ZWNlNGMwMzdlMWNkMDA5YTBiNTcyZDUzYzg0NjlmMGNlMGZmZTNiYThhMiJ9fX0="), "&aAlpha 电池&r");
			BETA_BATTERY = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIxNjcxN2YxZGE5NGY5OWJlODI0MmE5MzFlMzliYmYzMjI1MWRlZGY0NmNkMjA3M2ZmYTg4OTY5ZDc0Zjk2MyJ9fX0="), "&9Beta 电池&r");
			GAMMA_BATTERY = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgwZTJjMzNjNGM4MzI0NDUyYWNjZjkyMzU2NjllNjVmMDBkMTkyNmNjNTMzMTQ1MTkyNWNhMjZlNmFhNzIxIn19fQ=="), "&dGamma 电池&r");

			BASIC_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 4), "&e基础电力存储机&r", "", "&e基础电力存储机", "&8\u21E8 &e\u26A1 &7/**/640 J 容量");
			ADVANCED_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 1), "&d高级电力存储机&r", "", "&a中型电力存储机", "&8\u21E8 &e\u26A1 &7/**/2560 J 容量");
			ALPHA_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 5), "&e&lAlpha&e-α 电力存储机&r", "", "&e大型电力存储机", "&8\u21E8 &e\u26A1 &7/**/5120 J 容量");
			BETA_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 11), "&9&lBeta&9-β 电力存储机&r", "", "&e超级电力存储机", "&8\u21E8 &e\u26A1 &7/**/40960 J 容量");
			GAMMA_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 2), "&5&lGamma&5-γ 电力存储机&r", "", "&e终极电力存储机", "&8\u21E8 &e\u26A1 &7/**/327680 J 容量");
			LAMBDA_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 6), "&d&lLAMBDA&d-λ 电力存储机&r", "", "&f其实没必要搞这么大的电容了...吧......", "", "&e终极电力存储机", "&8\u21E8 &e\u26A1 &7/**/2621440 J 容量", "", Utils.db64s("Jjgmb+S9oOS7peS4uui/meWwseaYr+acgOWkp+WuuemHj+eahOeUteWuueS6huWQlz8="));
			KAPA_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS, 15), Utils.db64s("JjAmbEtBUEEmMC3OuiAmMOeUteWKm+WtmOWCqOacuiZy"), "", Utils.db64s("JmPlvakmZeibiyZh54mpJmLlk4Emcg=="), "", "&e终极电力存储机", Utils.db64s("JjhcdTIxRTggJmVcdTI2QTEgJjcyMDk3MTUyMCBKIOWuuemHjw=="), "", Utils.db64s("Jjgmb0ZyZWV6ZV9Eb2xwaGluIOW5tuS4jeaJk+eul+atouatpeS6juatpC4uLg=="));
			PHI_ELECTRICITY_STORAGE = new CustomItem(new UniversalMaterial(Material.STAINED_GLASS), Utils.db64s("JmYmbFAmNyZsSCZmJmxJJjctJmbPhiAmN+eUtSZm5YqbJjflrZgmZuWCqCY35py6JnI="), "", Utils.db64s("JmPlvakmZeibiyZh54mpJmLlk4Emcg=="), "", "&e终极电力存储机", Utils.db64s("JjhcdTIxRTggJmVcdTI2QTEgJjcxNjc3NzIxNjAgSiDlrrnph48="), "", Utils.db64s("Jmbov5novrnlu7rorq7mg7PopoHlvZPmnI3pnLjnmoTlhYjmiorov5nlgqjnlLXmnLrlhYXmu6HnlLU="));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UGItems(Loader plug) {

	}

}