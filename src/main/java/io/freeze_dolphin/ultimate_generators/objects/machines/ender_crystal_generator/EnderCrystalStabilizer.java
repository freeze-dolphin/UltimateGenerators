package io.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import io.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public class EnderCrystalStabilizer extends SlimefunItem {

	private static final int[] border = new int[] {
			0,   1,  2,  3,  4,  5,  6,  7,  8, 
			9,  10,     12,     14,         17, 
			18, 19, 20, 21, 22, 23, 24, 25, 26
	};

	private static final int slot = 11;
	private static final int insert = 15;
	private static final int export = 16;
	private static final int status = 13;

	private EnderCrystal ec;

	public EnderCrystalStabilizer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);

		new BlockMenuPreset(id, "&5末影水晶稳定器") {

			@Override
			public void init() {
				constructMenu(this);
			}

			@Override
			public void newInstance(BlockMenu menu, Block b) {
				boolean ex = false;
				try {
					menu.addItem(insert, new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRhMDRiMzJkMmI3NTk4ZjlkZDhlMjNmYjQwMTVjNjljM2NkOTQyYTM3YTllYTg0ZDA2ODY5ZjQ1OWYxIn19fQ=="), "&5插入水晶"), new MenuClickHandler() {

						@Override
						public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {	

							if (menu.getItemInSlot(slot).isSimilar(new ItemStack(Material.END_CRYSTAL)) && !isCrystalInserted(b)) {
								menu.replaceExistingItem(slot, InvUtils.decreaseItem(menu.getItemInSlot(slot), 1));
								BlockStorage.addBlockInfo(b, "crystal-inserted", "true");
								ec = (EnderCrystal) b.getWorld().spawnEntity(b.getLocation(), EntityType.ENDER_CRYSTAL);
								ec.setCustomName(ChatColor.translateAlternateColorCodes('&', "&f末影水晶 &a[稳定]&r"));
								ec.setGravity(false);
								ec.setCustomNameVisible(false);
							}

							return false;
						}

					});

					menu.addItem(export, new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk0MzI2YjUzOGVkOTg5ODcxMGQ1OGU1NTI0NzI2ZjMxMzAzNzM0NDgzZDFlNTIzN2VlMzI1YThiYmU1MjE3In19fQ=="), "&c导出水晶&r"), new MenuClickHandler() {

						@Override
						public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {

							ItemStack m = menu.getItemInSlot(slot).clone();
							if (((m.getType().equals(Material.END_CRYSTAL) && m.getAmount() < 64) || m == null) && isCrystalInserted(b)) {

								if (m.getType().equals(Material.END_CRYSTAL)) {									
									m.setAmount(m.getAmount() + 1);
									menu.replaceExistingItem(slot, m);
								} else {
									menu.replaceExistingItem(slot, new ItemStack(Material.END_CRYSTAL));
								}
								BlockStorage.addBlockInfo(b, "crystal-inserted", "false");
								ec.remove();
							}

							return false;
						}

					});
				} catch (Exception e) {
					ex = true;
				}

				if (!ex) newInstance(menu, b);
			}

			@Override
			public boolean canOpen(Block b, Player p) {
				return p.hasPermission("slimefun.inventory.bypass") || CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true);
			}

			@Override
			public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
				return null;
			}
		};
	}

	protected void constructMenu(BlockMenuPreset preset) {
		for (int i: border) {
			preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "),
					new MenuClickHandler() {

				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}

			});
		}

		preset.addItem(status, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " "),
				new MenuClickHandler() {

			@Override
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return false;
			}

		});

		preset.addMenuClickHandler(slot, new MenuClickHandler() {

			@Override
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return true;
			}
		});

	}

	@Override
	public void register(boolean slimefun) {
		addItemHandler(new BlockTicker() {

			@Override
			public void tick(Block b, SlimefunItem sf, Config data) {
				EnderCrystalStabilizer.this.tick(b);
			}

			@Override
			public void uniqueTick() {
			}

			@Override
			public boolean isSynchronized() {
				return false;
			}
		});

		super.register(slimefun);
	}

	private int getEnergyConsumption() {
		return 9;
	}

	protected void tick(Block b) {
		BlockMenu bm = BlockStorage.getInventory(b);

		bm.replaceExistingItem(slot, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), "&f机器信息", "&7 - &3耗电量: &e" + getEnergyConsumption() * 2 + " J/s", "&7 - &3水晶状态: &e" + (isCrystalInserted(b) ? "&a已插入" : "&c无")));

		if (ChargableBlock.getCharge(b) < getEnergyConsumption()) {
			if (ec.isValid()) {
				Snowball sb = (Snowball) ec.getWorld().spawnEntity(ec.getLocation(), EntityType.SNOWBALL);
				sb.getLocation().add(0D, -10D, 0D);
			}
		} else {
			ChargableBlock.addCharge(b, -getEnergyConsumption());
		}
	}

	@SuppressWarnings("deprecation")
	private boolean isCrystalInserted(Block b) {
		return BlockStorage.hasBlockInfo(b) && (BlockStorage.getBlockInfo(b, "crystal-inserted").equals("true")) && BlockStorage.getBlockInfo(b, "crystal-inserted") != null && BlockStorage.getBlockInfo(b, "crystal-inserted").equals("");
	}

}
