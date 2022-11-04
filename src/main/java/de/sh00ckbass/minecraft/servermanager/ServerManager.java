package de.sh00ckbass.minecraft.servermanager;

import co.aikar.commands.PaperCommandManager;
import de.sh00ckbass.minecraft.servermanager.config.ConfigLoader;
import de.sh00ckbass.minecraft.servermanager.config.Configs;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 03.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public final class ServerManager extends JavaPlugin {

    @Getter
    private final Configs configs = new Configs();

    private ConfigLoader configLoader;

    @Override
    public void onEnable() {
        this.configLoader = new ConfigLoader(this);
        this.configLoader.loadConfigs();
        this.registerCommands();
        this.registerListener();
    }

    private void registerCommands() {
        final PaperCommandManager commandManager = new PaperCommandManager(this);
    }

    private void registerListener() {
        final PluginManager pluginManager = this.getServer().getPluginManager();
    }

    @Override
    public void onDisable() {
        this.configLoader.saveConfigs();
    }

}
