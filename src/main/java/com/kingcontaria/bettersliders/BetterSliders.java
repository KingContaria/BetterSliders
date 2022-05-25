package com.kingcontaria.bettersliders;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterSliders implements ModInitializer {
    public static double newViewDistance;
    public static double newEntityDistance;
    public static final DoubleOption newRENDER_DISTANCE = new DoubleOption("options.renderDistance", 2.0, 32.0, 1.0F, (gameOptions) -> BetterSliders.newViewDistance, (gameOptions, double_) -> {
        BetterSliders.newViewDistance = double_;
    }, (gameOptions, doubleOption) -> {
        double d = doubleOption.get(gameOptions);
        return doubleOption.getDisplayPrefix().append(new TranslatableText("options.chunks", new Object[]{(int)d}));
    });
    public static final DoubleOption newENTITY_DISTANCE_SCALING = new DoubleOption("options.entityDistanceScaling", 0.5, 5.0, 0.25F, (gameOptions) -> BetterSliders.newEntityDistance, (gameOptions, double_) -> {
        BetterSliders.newEntityDistance = double_;
    }, (gameOptions, doubleOption) -> {
        double d = doubleOption.get(gameOptions);
        return doubleOption.getDisplayPrefix().append(new TranslatableText("options.entityDistancePercent", new Object[]{(int)(d * 100.0)}));
    });
    @Override
    public void onInitialize() {
    }
}
