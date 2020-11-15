package io.github.freeze_dolphin.ultimate_generators.objects.machines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Misc.compatibles.ProtectionUtils;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineHelper;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.freeze_dolphin.ultimate_generators.Loader;
import io.github.freeze_dolphin.ultimate_generators.objects.basics.UGConfig;
import io.github.freeze_dolphin.ultimate_generators.objects.basics.UniversalMaterial;

public abstract class UnproductiveGenerator extends SlimefunItem {
	public static Map<Block, MachineRecipe> processing = new HashMap<>();
	public static Map<Block, Integer> progress = new HashMap<>();
	protected List<MachineRecipe> recipes = new ArrayList<>();

	private static final int[] border = 
		{ 
				0,  1,  2,  3,  4,  5,  6,  7,  8, 
				9,                             17, 
				18, 19, 20, 21   , 23, 24, 25, 26
		};
	private static final int indicator = 22;

	public int[] getInputSlots() { return new int[] {10, 11, 12, 13, 14, 15, 16}; }

	private String ID;

	public UnproductiveGenerator(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);

		ID = id;

		new BlockMenuPreset(id, getInventoryTitle()) {
			public void init() {
				constructMenu(this);
			}

			public void newInstance(BlockMenu menu, Block b) {
			}

			public boolean canOpen(Block b, Player p) {
				boolean perm = (p.hasPermission("slimefun.inventory.bypass"))
						|| (CSCoreLib.getLib().getProtectionManager().canAccessChest(p.getUniqueId(), b, true));
				return (perm) && (ProtectionUtils.canAccessItem(p, b));
			}

			public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
				if (flow.equals(ItemTransportFlow.INSERT)) {
					return getInputSlots();
				}
				return null;
			}
		};
		registerBlockHandler(id, new SlimefunBlockHandler() {
			public void onPlace(Player p, Block b, SlimefunItem item) {
			}

			public boolean onBreak(Player p, Block b, SlimefunItem item, UnregisterReason reason) {
				BlockMenu inv = BlockStorage.getInventory(b);
				if (inv != null) {
					for (int slot : getInputSlots()) {
						if (inv.getItemInSlot(slot) != null) {
							b.getWorld().dropItemNaturally(b.getLocation(), inv.getItemInSlot(slot));
							inv.replaceExistingItem(slot, null);
						}
					}
				}
				UnproductiveGenerator.progress.remove(b);
				UnproductiveGenerator.processing.remove(b);
				return true;
			}
		});
		loadRecipes();
	}

	protected void constructMenu(BlockMenuPreset preset) {
		for (int i : border) {
			preset.addItem(i,
					new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 7), " ", new String[0]),
					new ChestMenu.MenuClickHandler() {
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}

		preset.addItem(indicator, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " ", new String[0]),
				new ChestMenu.MenuClickHandler() {
			public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
				return false;
			}
		});
	}

	public String getInventoryTitle() {
		return Loader.getUGConfig().getMachineInventoryTitle(getMachineIdentifier());
	}

	public abstract ItemStack getProgressBar();

	public void loadRecipes() {
		if (Loader.getUGConfig().contains(UGConfig.buildPath(getMachineIdentifier(), "machine-recipes"))) {
			for (MachineRecipe mr : Loader.getUGConfig().getMachineRecipes(getMachineIdentifier())) {
				registerRecipe(mr);
			}
		} else {
			registerDefaultRecipes();
			saveRecipesToFile();
		}
	}

	public abstract void registerDefaultRecipes();

	public int getSpeed() {
		return Loader.getUGConfig().getMachineSpeed(getMachineIdentifier());
	}

	public String getMachineIdentifier() {
		return ID;
	}

	public MachineRecipe getProcessing(Block b) {
		return (MachineRecipe) processing.get(b);
	}

	public boolean isProcessing(Block b) {
		return getProcessing(b) != null;
	}

	public void registerRecipe(MachineRecipe recipe) {
		recipe.setTicks(recipe.getTicks() / getSpeed());
		recipes.add(recipe);
	}

	public void saveRecipesToFile() {
		List<List<Object>> a = new ArrayList<>();
		for (MachineRecipe mr : recipes) {
			List<Object> b = new ArrayList<>();
			b.set(0, mr.getTicks() / 2);
			b.set(1, mr.getInput());
			b.set(2, mr.getOutput());
			a.add(b);
		}
		Loader.getUGConfig().setMachineValue(getMachineIdentifier(), "machine-recipes", a);
		Loader.getUGConfig().reload();
	}

	public void registerRecipe(int seconds, ItemStack[] input) {
		registerRecipe(new MachineRecipe(seconds, input, new ItemStack[] {}));
	}

	private Inventory inject(Block b) {
		int size = BlockStorage.getInventory(b).toInventory().getSize();
		Inventory inv = Bukkit.createInventory(null, size);
		for (int i = 0; i < size; i++) {
			inv.setItem(i, new CustomItem(Material.COMMAND, " &4ALL YOUR PLACEHOLDERS ARE BELONG TO US", 0));
		}
		return inv;
	}

	protected boolean fits(Block b, ItemStack[] items) {
		return inject(b).addItem(items).isEmpty();
	}

	public void register(boolean slimefun) {
		addItemHandler(new ItemHandler[]{new BlockTicker() {
			public void tick(Block b, SlimefunItem sf, Config data) {
				UnproductiveGenerator.this.tick(b);
			}

			public void uniqueTick() {
			}

			public boolean isSynchronized() {
				return false;
			}
		}});
		super.register(slimefun);
	}

	protected void tick(Block b) {
		ItemMeta im;
		if (isProcessing(b)) {
			int timeleft = ((Integer) progress.get(b)).intValue();
			if (timeleft > 0) {
				ItemStack item = getProgressBar().clone();
				item.setDurability(MachineHelper.getDurability(item, timeleft, ((MachineRecipe) processing.get(b)).getTicks()));
				im = item.getItemMeta();
				im.setDisplayName(" ");
				List<String> lore = new ArrayList<>();
				lore.add(MachineHelper.getProgress(timeleft, ((MachineRecipe) processing.get(b)).getTicks()));
				lore.add("");
				lore.add(MachineHelper.getTimeLeft(timeleft / 2));
				im.setLore(lore);
				item.setItemMeta(im);

				BlockStorage.getInventory(b).replaceExistingItem(indicator, item);
				progress.put(b, Integer.valueOf(timeleft - 1));
			} else {
				BlockStorage.getInventory(b).replaceExistingItem(indicator, new CustomItem(new UniversalMaterial(Material.STAINED_GLASS_PANE, 15), " ", new String[0]));

				progress.remove(b);
				processing.remove(b);
			}
		} else {
			MachineRecipe r = null;
			Map<Integer, Integer> found = new HashMap<>();
			for (MachineRecipe recipe : recipes) {
				for (ItemStack input : recipe.getInput()) {
					for (int slot : getInputSlots()) {
						if (SlimefunManager.isItemSimiliar(BlockStorage.getInventory(b).getItemInSlot(slot), input, true)) {
							found.put(Integer.valueOf(slot), Integer.valueOf(input.getAmount()));
							break;
						}
					}
				}
				if (found.size() == recipe.getInput().length) {
					r = recipe;
					break;
				}
				found.clear();
			}
			if (r != null) {
				if (!fits(b, r.getOutput())) {
					return;
				}
				for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
					BlockStorage.getInventory(b).replaceExistingItem(((Integer) entry.getKey()).intValue(), InvUtils.decreaseItem(BlockStorage.getInventory(b).getItemInSlot(((Integer) entry.getKey()).intValue()), ((Integer) entry.getValue()).intValue()));
				}
				processing.put(b, r);
				progress.put(b, Integer.valueOf(r.getTicks()));
			}
		}
	}
}