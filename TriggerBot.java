package com.nova.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;

/** Сам бьёт, когда прицел наведён на цель и оружие перезарядилось. */
public class TriggerBot extends Module {
    public final BoolSetting players = add(new BoolSetting("Игроки", false));
    public final BoolSetting mobs = add(new BoolSetting("Мобы", true));

    public TriggerBot() {
        super("TriggerBot");
    }

    @Override
    public void onTick(MinecraftClient mc) {
        ClientPlayerEntity p = mc.player;
        if (p == null || mc.interactionManager == null || mc.currentScreen != null) return;
        if (p.getAttackCooldownProgress(0.5f) < 1.0f) return;

        if (mc.crosshairTarget instanceof EntityHitResult hit
                && hit.getEntity() instanceof LivingEntity le
                && le.isAlive()
                && matches(le, mc, players, mobs)) {
            mc.interactionManager.attackEntity(p, le);
            p.swingHand(Hand.MAIN_HAND);
        }
    }
}
