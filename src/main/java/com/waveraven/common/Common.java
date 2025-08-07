package com.waveraven.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Common {
    // 原有常量定义
//    public static final int PLAYER_MAX_PHP = 250;
//    public static final int DEMON_MAX_PHP = 220;
//    public static final int DEMON_DAMAGE = 35;
    public static final int ADD_BLOOD = 20;
    public static final int SUBDAMAGE_BUFF_RATE = 50;
    public static final int FREEZE_DAMAGE = 20;
    public static final int FREEZE_RATE = 40;
    public static final int CAT_DAMAGE = 35;
    public static final int CATCRITE_RATE = 35;

    // 新增常量定义
    public static final int INITIAL_FEE = 500;  // 初始金钱
    public static final int DRAW_FEE = 20;      // 抽卡费用
    public static final int UP_FEE = 200;       // 升级费用

    // 商品价格
    public static final int STRAWBERRY = 15;    // 草莓
    public static final int BROOM = 30;         // 扫帚
    public static final int LUCKYSEED = 25;     // 幸运种子

    public static final String STRAWBERRY_DETAILS = "恢复10点生命值 ";
    public static final String BROOM_DETAILS = "装饰品，使用后提高魔法触发概率 ";
    public static final String LUCKYSEED_DETAILS = "使用后战斗获胜时金币收益+30 ";

    // 升级数据
    private static int currentLevel = 0;

    private static final Map<String, Map<Integer, Integer>> upgradeData = new HashMap<>();

    static {
        // 玩家最大HP
        Map<Integer, Integer> playerMaxHp = new HashMap<>();
        playerMaxHp.put(0, 300);
        playerMaxHp.put(1, 350);
        playerMaxHp.put(2, 400);
        upgradeData.put(UpgradeKeys.PLAYER_MAX_HP, playerMaxHp);

        // 恶魔最大HP
        Map<Integer, Integer> demonMaxHp = new HashMap<>();
        demonMaxHp.put(0, 280);
        demonMaxHp.put(1, 350);
        demonMaxHp.put(2, 480);
        upgradeData.put(UpgradeKeys.DEMON_MAX_HP, demonMaxHp);

        // 恶魔伤害
        Map<Integer, Integer> demonDamage = new HashMap<>();
        demonDamage.put(0, 35);
        demonDamage.put(1, 45);
        demonDamage.put(2, 55);
        upgradeData.put(UpgradeKeys.DEMON_DAMAGE, demonDamage);

        // 加血效果
        Map<Integer, Integer> addBlood = new HashMap<>();
        addBlood.put(0, 20);
        addBlood.put(1, 30);
        addBlood.put(2, 40);
        upgradeData.put(UpgradeKeys.ADD_BLOOD, addBlood);

        // 减伤概率
        Map<Integer, Integer> subdamageBuffRate = new HashMap<>();
        subdamageBuffRate.put(0, 50);
        subdamageBuffRate.put(1, 60);
        subdamageBuffRate.put(2, 70);
        upgradeData.put(UpgradeKeys.SUBDAMAGE_BUFF_RATE, subdamageBuffRate);

        // 冰冻伤害
        Map<Integer, Integer> freezeDamage = new HashMap<>();
        freezeDamage.put(0, 20);
        freezeDamage.put(1, 25);
        freezeDamage.put(2, 30);
        upgradeData.put(UpgradeKeys.FREEZE_DAMAGE, freezeDamage);

        // 冰冻概率
        Map<Integer, Integer> freezeRate = new HashMap<>();
        freezeRate.put(0, 40);
        freezeRate.put(1, 50);
        freezeRate.put(2, 60);
        upgradeData.put(UpgradeKeys.FREEZE_RATE, freezeRate);

        // 猫咪伤害
        Map<Integer, Integer> catDamage = new HashMap<>();
        catDamage.put(0, 35);
        catDamage.put(1, 40);
        catDamage.put(2, 50);
        upgradeData.put(UpgradeKeys.CAT_DAMAGE, catDamage);

        // 猫咪暴击率
        Map<Integer, Integer> catCriteRate = new HashMap<>();
        catCriteRate.put(0, 38);
        catCriteRate.put(1, 45);
        catCriteRate.put(2, 55);
        upgradeData.put(UpgradeKeys.CATCRITE_RATE, catCriteRate);

        // 战斗获胜金币
        Map<Integer, Integer> battleCoins = new HashMap<>();
        battleCoins.put(0, 25);
        battleCoins.put(1, 35);
        battleCoins.put(2, 45);
        upgradeData.put(UpgradeKeys.BATTLE_COINS, battleCoins);
    }

    // 模拟随机结果函数
    public static boolean randomResult(float probability) {
        Random random = new Random();
        return random.nextInt(100) < probability;
    }

    // 打印空格
    public static String printSpace() {
        return "          ";
    }

    // 格式化打印函数
    public static void formatPrint(String text) {
        System.out.println("******" + text + "******");
    }

    // 根据键和当前等级获取升级值
    public static int getUpgradeValue(String key) {
        if (upgradeData.containsKey(key)) {
            Map<Integer, Integer> levelMap = upgradeData.get(key);
            if (levelMap.containsKey(currentLevel)) {
                return levelMap.get(currentLevel);
            }
        }
        return 0; // 默认返回0，表示键不存在或等级不存在
    }

    // 升级等级
    public static void upgradeLevel() {
        currentLevel++;
        System.out.println(printSpace() + "等级提升 " + currentLevel + "!");
    }

    // 显示当前系统参数值
    public static void displayLevelValue() {
        System.out.println(printSpace() + "\n=== 当前等级 Stats (Level " + currentLevel + ") ===");
        System.out.println(printSpace() + "玩家最大 HP: " + getUpgradeValue(UpgradeKeys.PLAYER_MAX_HP));
        System.out.println(printSpace() + "恶魔最大 HP: " + getUpgradeValue(UpgradeKeys.DEMON_MAX_HP));
        System.out.println(printSpace() + "恶魔伤害: " + getUpgradeValue(UpgradeKeys.DEMON_DAMAGE));
        System.out.println(printSpace() + "药水加血值: " + getUpgradeValue(UpgradeKeys.ADD_BLOOD));
        System.out.println(printSpace() + "减伤概率: " + getUpgradeValue(UpgradeKeys.SUBDAMAGE_BUFF_RATE) + "%");
        System.out.println(printSpace() + "冰冻伤害: " + getUpgradeValue(UpgradeKeys.FREEZE_DAMAGE));
        System.out.println(printSpace() + "冰冻概率: " + getUpgradeValue(UpgradeKeys.FREEZE_RATE) + "%");
        System.out.println(printSpace() + "魔法猫伤害: " + getUpgradeValue(UpgradeKeys.CAT_DAMAGE));
        System.out.println(printSpace() + "猫咪暴击率: " + getUpgradeValue(UpgradeKeys.CATCRITE_RATE) + "%");
        System.out.println(printSpace() + "战斗获胜金币: " + getUpgradeValue(UpgradeKeys.BATTLE_COINS));
        System.out.println("============================================");
    }

    // 获取当前等级
    public static int getCurrentLevel() {
        return currentLevel;
    }

    // 设置当前等级（用于测试或其他用途）
    public static void setCurrentLevel(int level) {
        if (level >= 0) {
            currentLevel = level;
        }
    }
}
