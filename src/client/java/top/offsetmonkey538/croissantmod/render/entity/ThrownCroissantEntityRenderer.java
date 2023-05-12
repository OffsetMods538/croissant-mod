package top.offsetmonkey538.croissantmod.render.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import top.offsetmonkey538.croissantmod.entity.projectile.thrown.ThrownCroissantEntity;

public class ThrownCroissantEntityRenderer<T extends ThrownCroissantEntity> extends EntityRenderer<T> {
    private final ItemRenderer itemRenderer;
    private float lastRotationDegrees;

    public ThrownCroissantEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();


        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0f));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f));

        if (!entity.isInGround()) {
            final float currentRotationDegrees = (entity.world.getTime() + tickDelta) * 32;

            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(lastRotationDegrees + currentRotationDegrees));

            this.lastRotationDegrees = currentRotationDegrees;
        }

        this.itemRenderer.renderItem(entity.getStack(), ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.world, entity.getId());


        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(T entity) {
        return PlayerScreenHandler.BLOCK_ATLAS_TEXTURE;
    }
}
