package com.waveraven.entity;

import com.waveraven.common.Common;

public class Demon {
    // 属性
    private String demonName;
    private float demonDamage;
    private float demonPhp;

    private boolean isFreezed;
    private boolean isSubDamage;

    // 构造函数
    public Demon(String demonName, float demonDamage, float demonPhp) {
        this.demonName = demonName;
        this.demonDamage = demonDamage;
        this.demonPhp = demonPhp;
        this.isFreezed = false;
        this.isSubDamage = false;
    }

    // 显示恶魔信息
    public void showInfo() {
        System.out.println(Common.printSpace() + "恶魔名称：" + demonName +
                           "，恶魔血量：" + demonPhp +
                           "，是否被冰冻：" + isFreezed +
                           "，是否需要减伤：" + isSubDamage);
    }

    // 恶魔攻击玩家
    public void demonAttack(Player player) {
        float monDamage = demonDamage;

        if (isFreezed) {
            System.out.println(Common.printSpace() + "该恶魔被冰冻，无法攻击");
            return;
        }

        if (isSubDamage) {
            System.out.println(Common.printSpace() + "攻击造成减伤效果，伤害减半");
            monDamage = monDamage / 2;
        }

        System.out.println(Common.printSpace() + demonName + "发动攻击，造成的伤害为：" + monDamage);
        player.subPhp(monDamage);
    }

    // Getter 和 Setter
    public String getDemonName() {
        return demonName;
    }

    public void setDemonName(String demonName) {
        this.demonName = demonName;
    }

    public float getDemonDamage() {
        return demonDamage;
    }

    public void setDemonDamage(float demonDamage) {
        this.demonDamage = demonDamage;
    }

    public float getDemonPhp() {
        return demonPhp;
    }

    public void setDemonPhp(float demonPhp) {
        this.demonPhp = demonPhp;
    }

    public boolean isFreezed() {
        return isFreezed;
    }

    public void setFreezed(boolean freezed) {
        isFreezed = freezed;
    }

    public boolean isSubDamage() {
        return isSubDamage;
    }

    public void setSubDamage(boolean subDamage) {
        isSubDamage = subDamage;
    }
}
