package io.github.freeze_dolphin.ultimate_generators.objects.machines.ender_crystal_generator;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import io.github.freeze_dolphin.ultimate_generators.Utils;
import io.github.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public abstract class EnderCrystalStabilizer extends SlimefunItem {

	private static final int[] border = 
		{
				0,   1,  2,              6,  7,  8, 
				9,      11,             15,     17,  
				18, 19, 20,             24, 25, 26
		};

	private static final int[] borderCrystal = 
		{
				3,  4,  5,           
				12,     14, 
				21, 22, 23, 
		};

	public int[] getCrystalSlots() { return new int[] {13}; }

	private static final int switcher = 10;
	private static final int info = 16;

	private String ID;

	public EnderCrystalStabilizer(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);

		ID = id;

		new BlockMenuPreset(id, getInventoryTitle()) {
			public void init() {
				constructMenu(this);
			}

			public void newInstance(BlockMenu menu, Block b) {
				if (!BlockStorage.hasBlockInfo(b) || BlockStorage.getLocationInfo(b.getLocation(), "enabled") == null || BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals("false")) {
					menu.replaceExistingItem(switcher, new CustomItem(new UniversalMaterial(Material.SULPHUR), "&7启动状态: &4✘"));
					menu.addMenuClickHandler(switcher, new MenuClickHandler() {
						public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
							BlockStorage.addBlockInfo(b, "enabled", "true");
							newInstance(menu, b);
							return false;
						}
					});
				} else {
					menu.replaceExistingItem(switcher, new CustomItem(new UniversalMaterial(Material.REDSTONE), "&7启动状态: &2✔"));
					menu.addMenuClickHandler(switcher, new MenuClickHandler() {
						public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
							BlockStorage.addBlockInfo(b, "enabled", "false");
							newInstance(menu, b);
							return false;
						}
					});
				}
			}

			public boolean canOpen(Block b, Player p) {
				return Utils.reflectCanOpenMethod(b, p);
			}

			public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
				if (flow.equals(ItemTransportFlow.INSERT)) {
					return getCrystalSlots();
				}
				return getCrystalSlots();
			}
		};
		registerBlockHandler(id, new SlimefunBlockHandler() {
			public void onPlace(Player p, Block b, SlimefunItem item) {}

			public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
				BlockMenu inv = BlockStorage.getInventory(b);
				if (inv != null) {
					for (int slot : getCrystalSlots()) {
						if (inv.getItemInSlot(slot) != null) {
							b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
							inv.replaceExistingItem(slot, null);
						}
					}
				}
				return true;
			}
		});
	}

	protected void constructMenu(BlockMenuPreset preset) {
		for (int i : border) {
			preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " ", new String[0]), new MenuClickHandler() {
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		for (int i : borderCrystal) {
			preset.addItem(i, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 2), " ", new String[0]), new MenuClickHandler() {
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		preset.addItem(info, new CustomItem(new UniversalMaterial(Material.EMPTY_MAP), " "), new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	public abstract String getInventoryTitle();
	public abstract int getEnergyConsumption();

	public int getSpeed() { return 1; }

	public String getMachineIdentifier() { return ID; }

	public void register(boolean slimefun) {
		addItemHandler(new ItemHandler[]{new BlockTicker() {
			public void tick(Block b, SlimefunItem sf, Config data) {
				EnderCrystalStabilizer.this.tick(b);
			}

			public void uniqueTick() {}

			public boolean isSynchronized() {
				return false;
			}
		}});
		super.register(slimefun);
	}

	protected void tick(Block b) {

	}
}