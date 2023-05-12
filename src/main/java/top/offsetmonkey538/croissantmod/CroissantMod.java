package top.offsetmonkey538.croissantmod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.croissantmod.init.ModEntities;
import top.offsetmonkey538.croissantmod.init.ModItems;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

public class CroissantMod implements ModInitializer {
	public static final String MOD_ID = "croissant-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModStatusEffects.register();
		ModItems.register();
		ModEntities.register();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
