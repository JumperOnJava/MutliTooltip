package io.github.jumperonjava.multitooltipapi;

import net.minecraft.client.item.TooltipData;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class stores multiple TooltipData object to their further mapping to MultiTooltipComponent
 */
public class MultiTooltipData extends ArrayList<TooltipData> implements TooltipData {
    public MultiTooltipData(int length) {
        super(length);
    }
    public Optional<TooltipData> optional(){
        if(this.size() == 0){
            return Optional.empty();
        }
        return Optional.of(this);
    }
}
