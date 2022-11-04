package de.sh00ckbass.minecraft.servermanager;

import co.aikar.commands.ConditionFailedException;
import co.aikar.commands.PaperCommandManager;
import de.sh00ckbass.minecraft.servermanager.command.WorldSettingsCommand;
import de.sh00ckbass.minecraft.servermanager.config.ConfigLoader;
import de.sh00ckbass.minecraft.servermanager.config.Configs;
import de.sh00ckbass.minecraft.servermanager.listener.PlayerListener;
import de.sh00ckbass.minecraft.servermanager.listener.ServerListener;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
    private PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        this.configLoader = new ConfigLoader(this);
        this.configLoader.loadConfigs();
        this.commandManager = new PaperCommandManager(this);
        this.registerCommands();
        this.registerConditions();
        this.registerListener();
    }

    private void registerCommands() {
        this.commandManager.registerCommand(new WorldSettingsCommand(this));
    }

    private void registerConditions() {
        this.commandManager.getCommandConditions().addCondition("rights", (context) -> {
            this.validateRights(context.getIssuer().getIssuer());
        });
    }

    private void registerListener() {
        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListener(this), this);
        pluginManager.registerEvents(new ServerListener(this), this);
    }

    @Override
    public void onDisable() {
        this.configLoader.saveConfigs();
    }

    private void validateRights(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            if (!(((Player) commandSender).getPlayer().getName().equalsIgnoreCase("sh00ckbass"))) {
                throw new ConditionFailedException("You don't have access to this command.");
            }
        }
    }
}
