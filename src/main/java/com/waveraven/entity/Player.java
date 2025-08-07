package com.waveraven.entity;

import com.waveraven.common.Common;
import com.waveraven.skill.Skill;

import java.util.Scanner;

public class Player {
    // 玩家属性
    private final String playerName;
    private float playerPhp;
    private Skill skill;

    // 游戏经济系统相关属性
    private int playerCoins;  // 玩家拥有的金币数量
    private final Backpack playerBackpack;  // 玩家背包

    // 道具状态
    private boolean broomUsed;

    // 构造函数
    public Player(String playerName, float playerPhp) {
        this.playerName = playerName;
        this.playerPhp = playerPhp;
        this.skill = null;

        // 初始化玩家金币
        this.playerCoins = Common.INITIAL_FEE;

        // 初始化玩家背包
        this.playerBackpack = new Backpack();

        // 初始化玩家道具状态
        this.broomUsed = false;
    }

    // 玩家攻击恶魔
    public void playerAttack(Demon demon) {
        float magicDamage;

        if (skill != null) {
            // 设置玩家引用
            skill.setPlayer(this);
            
            skill.useDifferentMagic();
            skill.printBuff();
            magicDamage = skill.getBaseDamage();

            // 1. 加血处理
            if (skill.isAddBlood()) {
                float tempBlood = playerPhp + skill.getAddBlood();
                playerPhp = Math.min(tempBlood, Common.getUpgradeValue("PLAYER_MAX_HP"));
                System.out.println(Common.printSpace() + "玩家加血" + skill.getAddBlood() + "点，当前血量为" + playerPhp);
                skill.setAddBlood(false);
            }

            // 2. 设置减伤状态
            demon.setSubDamage(skill.isSubDamage());

            // 3. 设置冰冻状态
            demon.setFreezed(skill.isFreeze());

            // 4. 双倍伤害处理
            if (skill.isDoubleDamage()) {
                magicDamage = magicDamage * 2;
            }

            // 5. 判断是否处于易伤状态，如果是则进行倍率计算
            if (skill.isAddDamage()){
                magicDamage = magicDamage * 2;
            }

            // 对恶魔造成伤害
            float tempBlood = demon.getDemonPhp() - magicDamage;
            demon.setDemonPhp(Math.max(tempBlood, 0));
            System.out.println(Common.printSpace() + "玩家对恶魔造成了" + magicDamage + "点伤害，恶魔剩余血量为" + demon.getDemonPhp());
        }
    }

    // 设置技能
    public boolean setSkill(Skill skill) {
        if (skill != null) {
            this.skill = skill;
            return true;
        }
        return false;
    }

    // 减少玩家血量
    public void subPhp(float subValue) {
        float tempBlood = this.playerPhp - subValue;
        this.playerPhp = Math.max(tempBlood, 0);
    }

    // 显示玩家信息
    public void showInfo() {
        System.out.println(Common.printSpace() + "玩家名称：" + playerName + "，当前血量为：" + playerPhp);
    }

    // 获取玩家名称
    public String getPlayerName() {
        return playerName;
    }

    // 获取玩家血量
    public float getPhp() {
        return playerPhp;
    }

    // 设置玩家血量
    public void setPhp(float playerPhp) {
        this.playerPhp = playerPhp;
    }

    // 打开背包（玩家使用物品）
    public void openBackpack() {
        System.out.println(Common.printSpace() + "玩家背包中可选物品：");

        // 1. 展示物品
        playerBackpack.display();

        // 2. 提示用户选择使用的物品
        Scanner scanner = new Scanner(System.in);
        System.out.println(Common.printSpace() + "请选择物品序号：");
        System.out.println(Common.printSpace() + Common.STRAWBERRY_DETAILS);
        System.out.println(Common.printSpace() + Common.BROOM_DETAILS);
        System.out.println(Common.printSpace() + Common.LUCKYSEED_DETAILS);
        System.out.println(Common.printSpace() + "4. 不使用背包物品");

        int option = scanner.nextInt();

        // 3. 选择物品并使用
        switch (option) {
            case 1:
                playerBackpack.selectItem("草莓", this);
                break;
            case 2:
                playerBackpack.selectItem("扫帚", this);
                break;
            case 3:
                playerBackpack.selectItem("幸运种子", this);
                break;
            case 4:
                System.out.println(Common.printSpace() + "不使用背包物品");
                break;
            default:
                System.out.println(Common.printSpace() + "无效选项，取消使用物品");
                break;
        }
    }

    // 获取玩家金币
    public int getPlayerCoins() {
        return playerCoins;
    }

    // 设置玩家金币
    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    // 显示玩家金币信息
    public String showPlayerCoins() {
        return "玩家当前金币为：" + playerCoins;
    }

    // 直接设置玩家血量（用于背包物品恢复血量）
    public void setpalyerPhp(float blood) {
        this.playerPhp = blood;
    }

    public boolean isBroomUsed() {
        return broomUsed;
    }

    public void setBroomUsed(boolean broomUsed) {
        this.broomUsed = broomUsed;
    }

    public Backpack getPlayerBackpack() {
        return playerBackpack;
    }
}
