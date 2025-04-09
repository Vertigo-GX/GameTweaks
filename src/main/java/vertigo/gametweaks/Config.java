package vertigo.gametweaks;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class Config {

	private static final String SEPARATOR = " = ";
	private static final String DISABLE_LIGHTNING_STARTS_FIRES = "disableLightningStartsFires";
	private static final String DISABLE_PORTALS_SPAWN_PIGLINS = "disablePortalsSpawnPiglins";
	private static final String DISABLE_ENDERMEN_PICK_UP_BLOCKS = "disableEndermenPickUpBlocks";

	public boolean disableLightningStartsFires = false;
	public boolean disablePortalsSpawnPiglins = false;
	public boolean disableEndermenPickUpBlocks = false;

	public Config() {
		if(!read()){
			write();
		}
	}

	public void write() {
		File file = getFile();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(DISABLE_LIGHTNING_STARTS_FIRES + SEPARATOR + disableLightningStartsFires + System.lineSeparator());
			writer.write(DISABLE_PORTALS_SPAWN_PIGLINS + SEPARATOR + disablePortalsSpawnPiglins + System.lineSeparator());
			writer.write(DISABLE_ENDERMEN_PICK_UP_BLOCKS + SEPARATOR + disableEndermenPickUpBlocks);
		} catch (IOException e) {
			GameTweaks.LOGGER.error("Failed to write config ({})", file.getPath());
		}
	}

	public boolean read() {
		File file = getFile();
		if (!file.exists()) {
			return false;
		}
		try (BufferedReader reader = new BufferedReader((new FileReader(file)))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] segments = line.split(SEPARATOR);
				if (segments.length != 2 || segments[0].isEmpty() || segments[1].isEmpty()) {
					continue;
				}
				switch (segments[0]) {
					case DISABLE_LIGHTNING_STARTS_FIRES -> disableLightningStartsFires = segments[1].equals("true");
					case DISABLE_PORTALS_SPAWN_PIGLINS -> disablePortalsSpawnPiglins = segments[1].equals("true");
					case DISABLE_ENDERMEN_PICK_UP_BLOCKS -> disableEndermenPickUpBlocks = segments[1].equals("true");
				}
			}
		} catch (IOException e) {
			GameTweaks.LOGGER.error("Failed to read config ({})", file.getPath());
		}
		return true;
	}

	private File getFile() {
		return FabricLoader.getInstance().getGameDir().resolve("config").resolve(GameTweaks.MOD_ID + ".ini").toFile();
	}

}