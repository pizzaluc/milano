package com.pizzaluc.milano.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class UraniumItem extends Item {
    public UraniumItem() {
        super(new Item.Properties()
                .tab(ItemGroup.TAB_MATERIALS)
                .stacksTo(1));
    }
}
