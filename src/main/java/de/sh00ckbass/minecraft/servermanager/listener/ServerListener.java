package de.sh00ckbass.minecraft.servermanager.listener;

import de.sh00ckbass.minecraft.servermanager.ServerManager;
import org.bukkit.event.Listener;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 04.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ServerListener implements Listener {

    private final ServerManager plugin;

    public ServerListener(final ServerManager plugin) {
        this.plugin = plugin;
    }
}
