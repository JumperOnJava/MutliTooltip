package io.github.jumperonjava.multitooltipapi;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;

public class MutliTooltipApi implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        TooltipComponentCallback.EVENT.register((tooltipData)->{
            if(tooltipData instanceof MultiTooltipData multiTooltipData){
                return MultiTooltipComponent.of(multiTooltipData);
            }
            return null;
        });
    }
}
