package de.sh00ckbass.minecraft.servermanager.config;

import de.sh00ckbass.minecraft.servermanager.config.configs.WorldSettingsConfig;
import lombok.Getter;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 03.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@Getter
public class Configs {

    public WorldSettingsConfig worldSettingsConfig = new WorldSettingsConfig();

}
