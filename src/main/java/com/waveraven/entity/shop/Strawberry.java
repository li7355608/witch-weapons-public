package com.waveraven.entity.shop;

import com.waveraven.common.Common;
import com.waveraven.entity.Item;
import com.waveraven.entity.ItemType;
import com.waveraven.entity.Player;

public class Strawberry extends Item {

    public Strawberry() {
        setName("草莓");
        setType(ItemType.CONSUMABLE);
        setPrice(Common.STRAWBERRY);
        setDescription(Common.STRAWBERRY_DETAILS);
    }

    @Override
    public void useItem(Player player) {
        float currentBlood = player.getPhp();
        float maxBlood = Common.getUpgradeValue("PLAYER_MAX_HP");
        float restoreAmount = 10;

        float newBlood = Math.min(currentBlood + restoreAmount, maxBlood);
        player.setPhp(newBlood);

        Common.formatPrint("草莓恢复了10点生命值");
    }
}
