package com.nova.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    private final String name;
    private boolean enabled;
    private final List<BoolSetting> settings = new ArrayList<>();

    protected Module(String name) {
        this.name = name;
    }

    protected BoolSetting add(BoolSetting s) {
        settings.add(s);
        return s;
    }

    public String getName() { return name; }
    public boolean isEnabled() { return enabled; }
    public void toggle() { enabled = !enabled; }
    public List<BoolSetting> getSettings() { return settings; }

    public void onTick(MinecraftClient mc) {}

    /** Общая проверка цели: игроки и/или враждебные мобы. */
    protected static boolean matches(Entity e, MinecraftClient mc, BoolSetting players, BoolSetting mobs) {
        if (e == mc.player) return false;
        if (e instanceof PlayerEntity p) return players.get() && !p.isSpectator();
        if (e instanceof Monster) return mobs.get();
        return false;
    }
}
