package io.github.freeze_dolphin.ultimate_generators.objects.basics;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import io.github.freeze_dolphin.ultimate_generators.Loader;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

public class UGConfig extends Config {

	public UGConfig(Plugin plugin) {
		super(plugin);
	}

	public String getMachineValue(String machineID, String key) {
		return getConfiguration().getString(buildPath(machineID, key));
	}

	public void setMachineValue(String machineID, String key, Object value) {
		getConfiguration().set("machines." + machineID.replaceAll(".", "_") + "." + key, value);
	}

	public int getMachineValueAsInteger(String machineID, String key) {
		return getConfiguration().getInt(buildPath(machineID, key));
	}

	public double getMachineValueAsDouble(String machineID, String key) {
		return getConfiguration().getDouble(buildPath(machineID, key));
	}

	public int getMachineProduction(String machineID) {
		return contains(buildPath(machineID, "production")) ? getMachineValueAsInteger(machineID, "production") : getInt("defaults.production");
	}
	
	public int getMachineConsumption(String machineID) {
		return contains(buildPath(machineID, "consumption")) ? getMachineValueAsInteger(machineID, "consumption") : getInt("defaults.consumption");
	}

	public int getMachineProcessTime(String machineID) {
		return contains(buildPath(machineID, "process-time")) ? getMachineValueAsInteger(machineID, "process-time") : getInt("defaults.process-time");
	}

	public int getMachineSpeed(String machineID) {
		return contains(buildPath(machineID, "speed")) ? getMachineValueAsInteger(machineID, "speed") : getInt("defaults.speed");
	}
	
	public String getMachineInventoryTitle(String machineID) {
		Loader.debug(contains(buildPath(machineID, "inventory-title")) ? getMachineValue(machineID, "inventory-title") : getString("defaults.inventory-title"));
		return contains(buildPath(machineID, "inventory-title")) ? getMachineValue(machineID, "inventory-title") : getString("defaults.inventory-title");
	}
	
	@SuppressWarnings("unchecked")
	public MachineRecipe[] getMachineRecipes(String machineID) {
		if (!contains(buildPath(machineID, "machine-recipes"))) return null;
			
		List<List<Object>> machineRecipes = (List<List<Object>>) getConfiguration().getList(buildPath(machineID, "machine-recipes"));
		List<MachineRecipe> result = new ArrayList<>();
		for (List<Object> machineRecipe : machineRecipes) {
			int time = Integer.parseInt((String) machineRecipe.get(0));
			List<ItemStack> inputs = (List<ItemStack>) machineRecipe.get(1);
			List<ItemStack> outputs = (List<ItemStack>) machineRecipe.get(2);
			result.add(new MachineRecipe(time, inputs.toArray(new ItemStack[inputs.size()]), outputs.toArray(new ItemStack[outputs.size()])));
		}
		return result.toArray(new MachineRecipe[result.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public MachineFuel[] getMachineFuels(String machineID) {
		if (!contains(buildPath(machineID, "machine-fuels"))) return null;
		
		List<List<Object>> machineFuels = (List<List<Object>>) getConfiguration().getList(buildPath(machineID, "machine-fuels"));
		List<MachineFuel> result = new ArrayList<>();
		for (List<Object> machineFuel : machineFuels) {
			int time = Integer.parseInt((String) machineFuel.get(0));
			ItemStack input = (ItemStack) machineFuel.get(1);
			ItemStack output = (ItemStack) machineFuel.get(2);
			result.add(new MachineFuel(time, input, output));
		}
		return result.toArray(new MachineFuel[result.size()]);
	}


	public static String buildPath(String machineID, String key) {
		return ("machines." + machineID.replaceAll("\\.", "_") + "." + key);
	}

}
