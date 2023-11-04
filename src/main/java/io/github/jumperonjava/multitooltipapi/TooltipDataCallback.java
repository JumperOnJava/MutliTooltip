package io.github.jumperonjava.multitooltipapi;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import java.util.Optional;

@FunctionalInterface
public interface TooltipDataCallback {
    /**
     * Allows registering custom TooltipData object for item.
     * This allows you to add your own tooltips to existing items
     * Tooltip data rendering should be registered using TooltipComponentCallback,
     * otherwise game will crash when trying to map TooltipData to TooltipComponent
     * If you don't need to add tooltip data to this specific itemStack you can return Optional.empty()
     */
    public static final Event<TooltipDataCallback> EVENT = EventFactory.createArrayBacked(TooltipDataCallback.class, callbacks -> itemStack -> {
        MultiTooltipData data = new MultiTooltipData(callbacks.length);
        for(TooltipDataCallback callback : callbacks){
            callback.getTooltipData(itemStack).ifPresent(data::add);
        }
        return data.optional();
    });
    Optional<TooltipData> getTooltipData(ItemStack itemStack);
}
