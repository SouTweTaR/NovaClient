package com.nova.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

/** Бьёт ближайшую подходящую цель в радиусе, когда оружие перезарядилось. */
public class KillAura extends Module {
    private static final double RANGE = 3.5;

    public final BoolSetting players = add(new BoolSetting("Игроки", false));
    public final BoolSetting mobs = add(new BoolSetting("Мобы", true));

    public KillAura() {
        super("KillAura");
    }

    @Override
    public void onTick(MinecraftClient mc) {
        ClientPlayerEntity p = mc.player;
        if (p == null || mc.world == null || mc.interactionManager == null) return;
        if (mc.currentScreen != null) return;
        if (p.getAttackCooldownProgress(0.5f) < 1.0f) return;

        LivingEntity best = null;
        double bestDist = RANGE * RANGE;
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof LivingEntity le) || !le.isAlive()) continue;
            if (!matches(le, mc, players, mobs)) continue;
            double d = p.squaredDistanceTo(le);
            if (d <= bestDist) {
                bestDist = d;
                best = le;
            }
        }

        if (best != null) {
            mc.interactionManager.attackEntity(p, best);
            p.swingHand(Hand.MAIN_HAND);
        }
    }
}
