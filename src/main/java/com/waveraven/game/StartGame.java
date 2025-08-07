package com.waveraven.game;

import com.waveraven.battle.Fight;
import com.waveraven.common.Common;
import com.waveraven.entity.Player;
import com.waveraven.shop.MyShop;

import java.util.Scanner;

public class StartGame {
    // 玩家对象
    private final Player player;
    // 商店对象
    private final MyShop shop;

    // 构造函数
    public StartGame() {
        this.player = new Player("女巫", Common.getUpgradeValue("PLAYER_MAX_HP"));
        this.shop = new MyShop();
    }

    // 启动游戏
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            // 显示主菜单
            System.out.println("\n------欢迎来到女巫世界------ 当前金币：" + player.showPlayerCoins());
            System.out.println(Common.printSpace() + "1. 进入商店  2. 战斗  3. 查看当前属性  4. 升级  5. 抽卡  6. 退出游戏");

            int option = scanner.nextInt();

            // 设置商店关联玩家
            shop.setPlayer(player);

            switch (option) {
                case 1:
                    System.out.println(Common.printSpace() + "进入商店");
                    shop.playerEnter(player);
                    break;

                case 2:
                    System.out.println(Common.printSpace() + "开始战斗");
                    Fight fight = new Fight();
                    // 默认战斗模式
//                    fight.battle1v1();
                    fight.battle(player);
                    break;

                case 3:
                    System.out.println(Common.printSpace() + "打印当前游戏属性");
                    Common.displayLevelValue();
                    break;

                case 4:
                    if (shop.checkMoney(Common.UP_FEE)) {
                        System.out.println(Common.printSpace() + "升级");
                        Common.upgradeLevel();
                        // 更新玩家最大血量
                        player.setPhp(Common.getUpgradeValue("PLAYER_MAX_HP"));
                    } else {
                        System.out.println(Common.printSpace() + "金钱不足，无法升级");
                    }
                    break;

                case 5:
                    System.out.println(Common.printSpace() + "抽卡");
                    shop.signalCardDraw(player);
                    break;

                case 6:
                    System.out.println(Common.printSpace() + "退出");
                    isRunning = false;
                    break;

                default:
                    System.out.println("无效选项，请重新选择");
                    break;
            }
        }

        System.out.println("游戏结束");
    }

    // 获取当前玩家对象（用于测试）
    public Player getPlayer() {
        return player;
    }

    // 获取商店对象（用于测试）
    public MyShop getShop() {
        return shop;
    }
}
