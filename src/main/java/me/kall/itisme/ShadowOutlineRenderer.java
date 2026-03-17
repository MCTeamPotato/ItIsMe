package me.kall.itisme;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class ShadowOutlineRenderer {
    private static final ThreadLocal<Boolean> SHADOW_RENDERED = new ThreadLocal<>();

    public static void setShadowRendered() {
        SHADOW_RENDERED.set(Boolean.TRUE);
    }

    public static boolean isShadowRendered() {
        boolean rendered = SHADOW_RENDERED.get() == Boolean.TRUE;
        SHADOW_RENDERED.remove();
        return rendered;
    }

    public static void renderShadowOutline(PoseStack poseStack, MultiBufferSource buffer, @NotNull Entity entity, float weight, float partialTicks, LevelReader level, float size) {
        OutlineColor shadowColor = ItIsMeConfig.getEntityShadowOutlineColor(entity);
        if (shadowColor == null) return;
        double entityX = Mth.lerp(partialTicks, entity.xOld, entity.getX());
        double entityY = Mth.lerp(partialTicks, entity.yOld, entity.getY());
        double entityZ = Mth.lerp(partialTicks, entity.zOld, entity.getZ());

        float f1 = Math.min(weight / 0.5F, size);
        int minY = Mth.floor(entityY - f1);
        int maxY = Mth.floor(entityY);

        double groundY = entityY;
        boolean foundGround = false;
        BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(Mth.floor(entityX), 0, Mth.floor(entityZ));

        for (int y = maxY; y >= minY; y--) {
            mPos.setY(y);
            BlockState state = level.getBlockState(mPos);
            if (state.getRenderShape() != RenderShape.INVISIBLE && state.isCollisionShapeFullBlock(level, mPos) && !state.getShape(level, mPos).isEmpty()) {
                groundY = y + state.getShape(level, mPos).max(Direction.Axis.Y);
                foundGround = true;
                break;
            }
        }

        if (!foundGround) return;

        poseStack.pushPose();

        float yOffset = (float) (groundY - entityY) + 0.015f;
        poseStack.translate(0, yOffset, 0);

        VertexConsumer lineBuffer = buffer.getBuffer(RenderType.lines());
        Matrix4f pose = poseStack.last().pose();
        int r = shadowColor.red;
        int g = shadowColor.green;
        int b = shadowColor.blue;
        int a = shadowColor.alpha;

        int segments = 64;
        float angleStep = (float) (Math.PI * 2.0 / segments);

        for (int i = 0; i < segments; i++) {
            float angle1 = i * angleStep;
            float angle2 = (i + 1) * angleStep;

            float x1 = Mth.cos(angle1) * size;
            float z1 = Mth.sin(angle1) * size;
            float x2 = Mth.cos(angle2) * size;
            float z2 = Mth.sin(angle2) * size;

            lineBuffer.addVertex(pose, x1, 0, z1).setColor(r, g, b, a).setNormal(0, 1, 0);
            lineBuffer.addVertex(pose, x2, 0, z2).setColor(r, g, b, a).setNormal(0, 1, 0);
        }

        poseStack.popPose();
    }
}
