package top.offsetmonkey538.croissantmod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CroissantMod implements ModInitializer {
	public static final String MOD_ID = "croissant-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Do stuff
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
