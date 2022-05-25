package com.kingcontaria.bettersliders.mixin;

import com.kingcontaria.bettersliders.BetterSliders;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.VideoOptionsScreen;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin {

    @Mutable
    @Shadow @Final private static Option[] OPTIONS;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addAutoFreezeOption(Screen parent, GameOptions gameOptions, CallbackInfo ci){
        BetterSliders.newViewDistance = gameOptions.viewDistance;
        BetterSliders.newEntityDistance = gameOptions.entityDistanceScaling;
        int i = 0;
        for(Option option : OPTIONS){
            if(option == Option.RENDER_DISTANCE){
                OPTIONS[i] = BetterSliders.newRENDER_DISTANCE;
            }else {
                if(option == Option.ENTITY_DISTANCE_SCALING){
                    OPTIONS[i] = BetterSliders.newENTITY_DISTANCE_SCALING;
                }
            }
            i++;
        }
    }

    @Inject(method = "removed",at = @At("HEAD"))
    private void changeSettings(CallbackInfo ci){
        MinecraftClient.getInstance().options.viewDistance = (int) BetterSliders.newViewDistance;
        MinecraftClient.getInstance().options.entityDistanceScaling = (float) BetterSliders.newEntityDistance;
    }

}
