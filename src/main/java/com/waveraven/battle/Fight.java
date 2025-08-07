package com.waveraven.battle;

import com.waveraven.common.Common;
import com.waveraven.common.UpgradeKeys;
import com.waveraven.entity.Demon;
import com.waveraven.entity.Player;
import com.waveraven.skill.HealingSpell;
import com.waveraven.skill.Freeze;
import com.waveraven.skill.MagicCat;
import com.waveraven.skill.Skill;

import java.util.Scanner;

public class Fight {
    // 1v1 战斗模式
    public void battle1v1() {
        int count = 0;

        // 实例化玩家
        Player player = new Player("女巫", Common.getUpgradeValue(UpgradeKeys.PLAYER_MAX_HP));

        // 实例化恶魔
        Demon demon = new Demon("恶魔君", Common.getUpgradeValue(UpgradeKeys.DEMON_DAMAGE), Common.getUpgradeValue(UpgradeKeys.DEMON_MAX_HP));

        // 实例化技能
        Skill skill;

        Scanner scanner = new Scanner(System.in);

        // 战斗循环
        while (true) {
            count++;
            System.out.println("===============当前回合 " + count + "===============");
            
            // 重置玩家扫帚使用状态
            player.setBroomUsed(false);

            // 选择技能
            skill = selectSkill(player, scanner);

            // 绑定技能到玩家
            boolean status = player.setSkill(skill);
            if (!status) {
                System.out.println("无法绑定玩家技能, 技能无效，请重新选择");
                continue;
            }

            // 玩家攻击
            Common.formatPrint("玩家开始攻击");
            player.playerAttack(demon);

            // 检查恶魔是否被击败
            if (demon.getDemonPhp() <= 0) {
                System.out.println("\n!!!!!! 玩家已获得胜利！");
                return;
            }

            // 恶魔攻击
            Common.formatPrint("恶魔开始攻击");
            demon.demonAttack(player);

            // 检查玩家是否被击败
            if (player.getPhp() <= 0) {
                System.out.println("\n!!!!!! 玩家已被击败！");
                return;
            }

            // 显示战斗状态
            player.showInfo();
            demon.showInfo();
            System.out.println("===============回合 " + count + "结束===============");
            System.out.println();
        }
    }

    // 带玩家的战斗模式（支持背包和技能）
    public void battle(Player player) {
        int round = 0;

        // 设置玩家满血
        player.setPhp(Common.getUpgradeValue(UpgradeKeys.PLAYER_MAX_HP));

        // 实例化恶魔
        Demon demon = new Demon("恶魔君", Common.getUpgradeValue(UpgradeKeys.DEMON_DAMAGE), Common.getUpgradeValue(UpgradeKeys.DEMON_MAX_HP));

        // 战斗循环
        while (true) {
            round++;
            System.out.println("===============当前回合 " + round + "===============");
            
            // 重置玩家扫帚使用状态，如果改为全流程有效则注释此字段，只在初始化战斗的时候进行状态重置
            player.setBroomUsed(false);

            // 玩家打开背包
            player.openBackpack();

            // 显示背包物品
            player.getPlayerBackpack().display();

            // 选择技能
            Skill skill = selectSkill(player, new Scanner(System.in));
            boolean status = player.setSkill(skill);
            if (!status) {
                System.out.println("技能绑定失败，将跳过本回合攻击");
            }

            // 玩家攻击
            Common.formatPrint("玩家开始攻击");
            player.playerAttack(demon);

            if (demon.getDemonPhp() <= 0) {
                System.out.println("\n!!!!!! 玩家已获得胜利！");
                // 奖励金币
                player.setPlayerCoins(player.getPlayerCoins() + Common.getUpgradeValue(UpgradeKeys.BATTLE_COINS));
                System.out.println(player.showPlayerCoins());
                return;
            }

            Common.formatPrint("恶魔开始攻击");
            demon.demonAttack(player);

            if (player.getPhp() <= 0) {
                System.out.println("\n!!!!!! 玩家已被击败！");
                return;
            }

            // 显示战斗状态
            player.showInfo();
            demon.showInfo();
            System.out.println("===============回合 " + round + "结束===============");
            System.out.println();
        }
    }

    // 玩家选择技能
    private Skill selectSkill(Player player, Scanner scanner) {
        System.out.println(Common.printSpace() + "请选择技能：");
        System.out.println(Common.printSpace() + "1. 药水  恢复生命值，有概率进入减伤状态");
        System.out.println(Common.printSpace() + "2. 冰冻  有概率冰冻目标，被冰冻的目标无法行动且进入易伤状态");
        System.out.println(Common.printSpace() + "3. 魔法猫 有概率造成双倍伤害");

        int option = scanner.nextInt();

        return switch (option) {
            case 1 -> {
                System.out.println(Common.printSpace() + "您选择了药水");
                yield new HealingSpell();
            }
            case 2 -> {
                System.out.println(Common.printSpace() + "您选择了冰冻");
                yield new Freeze();
            }
            case 3 -> {
                System.out.println(Common.printSpace() + "您选择了魔法猫");
                yield new MagicCat();
            }
            default -> {
                System.out.println("无效选项，将默认选择药水");
                yield new HealingSpell();
            }
        };
    }
}
