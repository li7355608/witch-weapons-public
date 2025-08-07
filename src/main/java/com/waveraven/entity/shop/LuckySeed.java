package com.waveraven.entity.shop;

import com.waveraven.common.Common;
import com.waveraven.entity.Item;
import com.waveraven.entity.ItemType;
import com.waveraven.entity.Player;

public class LuckySeed extends Item {

    public LuckySeed() {
        setName("幸运种子");
        setType(ItemType.FUNCTIONAL);
        setPrice(Common.LUCKYSEED);
        setDescription(Common.LUCKYSEED_DETAILS);
    }

    @Override
    public void useItem(Player player) {
        // 假设战斗胜利后增加金币
        int bonusCoins = 30;
        player.setPlayerCoins(player.getPlayerCoins() + bonusCoins);
        Common.formatPrint("使用了幸运种子");
        System.out.println(Common.printSpace() + player.showPlayerCoins());
    }
}
