package com.nova.client.module;

import java.util.List;

public final class ModuleManager {
    public static final KillAura KILL_AURA = new KillAura();
    public static final TriggerBot TRIGGER_BOT = new TriggerBot();
    public static final Esp ESP = new Esp();

    public static final List<Module> MODULES = List.of(KILL_AURA, TRIGGER_BOT, ESP);

    private ModuleManager() {}
}
