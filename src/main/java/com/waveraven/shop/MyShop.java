package com.waveraven.shop;

import com.waveraven.common.Common;
import com.waveraven.entity.Player;
import com.waveraven.entity.shop.Strawberry;
import com.waveraven.entity.shop.Broom;
import com.waveraven.entity.shop.LuckySeed;
import com.waveraven.shop.card.Character;
import com.waveraven.shop.card.Rarity;
import com.waveraven.utils.ShopUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyShop {
    private Player player;

    public MyShop() {
        this.player = null;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // 玩家进入商店购买物品
    public void playerEnter(Player player) {
        this.player = player;

        Scanner scanner = new Scanner(System.in);

        System.out.println("商店中的商品：");
        System.out.println(Common.printSpace() + Common.STRAWBERRY_DETAILS + Common.STRAWBERRY + "金币");
        System.out.println(Common.printSpace() + Common.BROOM_DETAILS + Common.BROOM + "金币");
        System.out.println(Common.printSpace() + Common.LUCKYSEED_DETAILS + Common.LUCKYSEED + "金币");

        System.out.print("请选择商品序号（1-3）：");
        int option = scanner.nextInt();

        if (player.getPlayerBackpack() == null) {
            System.out.println("背包未初始化");
            return;
        }

        switch (option) {
            case 1:
                if (checkMoney(Common.STRAWBERRY)) {
                    player.getPlayerBackpack().addItem(new Strawberry());
                    System.out.println("购买草莓成功");
                }
                break;
            case 2:
                if (checkMoney(Common.BROOM)) {
                    player.getPlayerBackpack().addItem(new Broom());
                    System.out.println("购买扫帚成功");
                }
                break;
            case 3:
                if (checkMoney(Common.LUCKYSEED)) {
                    player.getPlayerBackpack().addItem(new LuckySeed());
                    System.out.println("购买幸运种子成功");
                }
                break;
            default:
                System.out.println("无效选项");
                break;
        }

        player.getPlayerBackpack().display();
    }

    // 检查金币是否足够
    public boolean checkMoney(int pay) {
        if (player != null) {
            if (player.getPlayerCoins() >= pay) {
                player.setPlayerCoins(player.getPlayerCoins() - pay);
                System.out.println("花费" + pay + "金币，剩余：" + player.showPlayerCoins());
                return true;
            } else {
                System.out.println("金币不足");
            }
        }
        return false;
    }

    // 初始化角色池
    public List<Character> initializePool() {
        List<Character> pool = new ArrayList<>();
        pool.add(new Character("莉莉", "圣光治愈", Rarity.R));
        pool.add(new Character("露露", "魔法飞弹", Rarity.R));
        pool.add(new Character("奥古斯丁", "战士", Rarity.R));
        pool.add(new Character("莉娜", "火焰之剑", Rarity.R));
        pool.add(new Character("伊莱克斯", "双枪", Rarity.SR));
        pool.add(new Character("光之骑士", "约尔荣耀之剑", Rarity.SR));
        pool.add(new Character("暗影巫师", "暗影之刃", Rarity.SR));
        pool.add(new Character("德古拉", "吸血鬼伯爵", Rarity.SSR));
        pool.add(new Character("阿尔泰拉", "影之女王", Rarity.UR));
        return pool;
    }

    // 根据稀有度抽取角色
    public Character drawSpecificRarity(List<Character> pool, Rarity rarity) {
        List<Character> candidates = new ArrayList<>();
        for (Character ch : pool) {
            if (ch.getRarity() == rarity) {
                candidates.add(ch);
            }
        }

        if (candidates.isEmpty()) {
            return null;
        }

        Random random = new Random();
        return candidates.get(random.nextInt(candidates.size()));
    }

    // 按照概率抽取角色
    public Character drawCard(List<Character> pool) {
        Random random = new Random();
        int randNum = random.nextInt(100) + 1;

        if (randNum <= 10) {
            return drawSpecificRarity(pool, Rarity.UR);
        } else if (randNum <= 30) {
            return drawSpecificRarity(pool, Rarity.SSR);
        } else if (randNum <= 60) {
            return drawSpecificRarity(pool, Rarity.SR);
        } else {
            return drawSpecificRarity(pool, Rarity.R);
        }
    }

    // 单次抽卡
    public void signalCardDraw(Player player) {
        this.player = player;

        if (checkMoney(Common.DRAW_FEE)) {
            List<Character> pool = initializePool();
            Character ch = drawCard(pool);
            if (ch != null) {
                String color = ShopUtils.getRarityColor(ch.getRarity());
                displayCharacter(ch, color);
            } else {
                System.out.println("抽卡失败，角色池为空");
            }
        } else {
            System.out.println("金币不足，无法抽卡");
        }
    }

    /**
     * 这个函数用于输出卡牌信息，可输出彩色文本。
     * 在输出文本前使用转义彩色字符便可以实现文字彩色输出，输出完毕后再输出重置字符便可以恢复原貌
     * @param ch 角色卡数据
     * @param color 角色稀有度颜色
     */
    public void displayCharacter(Character ch, String color) {
        String rarityStr = ch.getRarity().name();
        System.out.println(color);

        System.out.println("----------------------------");
        System.out.println("抽到的角色：");
        System.out.println("名称: " + ch.getName());
        System.out.println("稀有度: " + rarityStr);
        System.out.println("技能: " + ch.getSkill());
        System.out.println("----------------------------");

        System.out.println(ShopUtils.resetColor());
    }
}
