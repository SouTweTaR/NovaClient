package com.nova.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

/** Подсветка сущностей контуром сквозь стены (как эффект свечения). */
public class Esp extends Module {
    public final BoolSetting players = add(new BoolSetting("Игроки", true));
    public final BoolSetting mobs = add(new BoolSetting("Мобы", false));

    public Esp() {
        super("ESP");
    }

    public boolean shouldHighlight(Entity e) {
        return isEnabled() && matches(e, MinecraftClient.getInstance(), players, mobs);
    }
}
