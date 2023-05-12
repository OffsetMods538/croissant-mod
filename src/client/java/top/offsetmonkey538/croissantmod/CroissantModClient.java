package top.offsetmonkey538.croissantmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import top.offsetmonkey538.croissantmod.init.ModEntities;
import top.offsetmonkey538.croissantmod.render.entity.ThrownCroissantEntityRenderer;

public class CroissantModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.CROISSANT, ThrownCroissantEntityRenderer::new);
	}
}
