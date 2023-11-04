package io.github.jumperonjava.multitooltipapi.mixin;

import io.github.jumperonjava.multitooltipapi.MultiTooltipData;
import io.github.jumperonjava.multitooltipapi.TooltipDataCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(HandledScreen.class)
class ItemTooltipMixin {
    @Redirect(method = "drawMouseoverTooltip",at = @At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;getTooltipData()Ljava/util/Optional;"))
    Optional<TooltipData> addMultiData(ItemStack stack){
        var original = stack.getTooltipData();
        var mutlidata = (MultiTooltipData) TooltipDataCallback.EVENT.invoker()
                .getTooltipData(stack)
                .orElse(new MultiTooltipData(0));
        original.ifPresent(mutlidata::add);
        return mutlidata.optional();
    }
}
