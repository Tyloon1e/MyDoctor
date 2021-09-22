package me.Tyloon1e.MyDoctor;

import org.bukkit.plugin.java.JavaPlugin;

public class MyDoctor extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getCommand("mydoctor").setExecutor(new Heal());
	}

}
