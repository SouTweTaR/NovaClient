NovaClient — Fabric-мод для Minecraft 1.21.4

СБОРКА
1. Установи Java 21 (JDK) — например, Temurin 21: https://adoptium.net
2. Установи IntelliJ IDEA Community (бесплатно).
3. Распакуй архив, в IntelliJ: File → Open → выбери папку NovaClient.
   Подожди, пока Gradle всё скачает (первый раз 5–10 минут).
4. Справа вкладка Gradle → NovaClient → Tasks → build → двойной клик по "build".
5. Готовый мод: build/libs/novaclient-1.0.0.jar

Если сборка ругается на версии — открой https://fabricmc.net/develop,
выбери 1.21.4 и перепиши версии оттуда в gradle.properties.

УСТАНОВКА
1. Установи Fabric Loader для 1.21.4: https://fabricmc.net/use/installer
2. Положи в папку .minecraft/mods:
   - novaclient-1.0.0.jar
   - Fabric API для 1.21.4 (https://modrinth.com/mod/fabric-api)
3. Запусти профиль fabric-loader-1.21.4.

УПРАВЛЕНИЕ
- Правый Shift — открыть меню. Клавишу можно сменить:
  Настройки → Управление → Привязка клавиш → NovaClient.
- В меню жмёшь на функцию — ВКЛ/ВЫКЛ. Под каждой функцией — выбор целей
  (Игроки / Мобы).
- ESC — закрыть меню.

ФУНКЦИИ
- KillAura   — бьёт ближайшую цель в радиусе 3.5 блока.
- TriggerBot — бьёт, когда прицел наведён на цель.
- ESP        — контур вокруг игроков/мобов сквозь стены.
