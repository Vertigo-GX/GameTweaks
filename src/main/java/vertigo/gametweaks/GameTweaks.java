package vertigo.gametweaks;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameTweaks implements ModInitializer {

	public static final String MOD_ID = "game-tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Config CONFIG = new Config();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing");
	}

}