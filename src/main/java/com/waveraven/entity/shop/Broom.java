package com.waveraven.entity.shop;

import com.waveraven.common.Common;
import com.waveraven.entity.Item;
import com.waveraven.entity.ItemType;
import com.waveraven.entity.Player;

public class Broom extends Item {

    public Broom() {
        setName("扫帚");
        setType(ItemType.DECORATION);
        setPrice(Common.BROOM);
        setDescription(Common.BROOM_DETAILS);
    }

    @Override
    public void useItem(Player player) {
        Common.formatPrint("使用了扫帚，此回合法魔法获得强化");
        player.setBroomUsed(true);
    }
}
