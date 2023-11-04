package io.github.jumperonjava.multitooltipapi;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import java.util.Optional;

@FunctionalInterface
public interface TooltipDataCallback {
    public static final Event<TooltipDataCallback> EVENT = EventFactory.createArrayBacked(TooltipDataCallback.class, callbacks -> itemStack -> {
        MultiTooltipData data = new MultiTooltipData(callbacks.length);
        for(TooltipDataCallback callback : callbacks){
            callback.getTooltipData(itemStack).ifPresent(data::add);
        }
        return data.optional();
    });
    Optional<TooltipData> getTooltipData(ItemStack itemStack);
}
