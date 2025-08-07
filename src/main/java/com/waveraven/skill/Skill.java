package com.waveraven.skill;


import com.waveraven.common.Common;
import com.waveraven.entity.Player;

public abstract class Skill {

    // 添加 Player 引用
    protected Player player;

    // 技能状态属性
    protected boolean isSubDamage;
    protected boolean isAddBlood;
    protected boolean isFreeze;
    protected boolean isDoubleDamage;
    protected boolean isAddDamage;

    // 技能基本信息
    protected String skillName;
    protected float baseDamage;
    protected float addBlood;
    protected float buffRate;
    protected float freezeRate;
    protected float criteRate;

    // 构造函数
    public Skill() {
        this.isSubDamage = false;
        this.isAddBlood = false;
        this.isFreeze = false;
        this.isDoubleDamage = false;
        this.isAddDamage = false;

        this.skillName = "defaultname";
        this.baseDamage = 0;
        this.addBlood = 0;
        this.buffRate = 0;
        this.freezeRate = 0;
        this.criteRate = 0;
    }

    // 设置使用此技能的玩家
    public void setPlayer(Player player) {
        this.player = player;
    }

    // 使用不同的魔法（抽象方法，由子类实现）
    public abstract void useDifferentMagic();

    // 判断是否触发增益效果
    public boolean buffPotion() {
        if (Common.randomResult(buffRate)) {
            System.out.println(Common.printSpace() + "成功触发增益效果");
            return true;
        } else {
            System.out.println(Common.printSpace() + "未触发增益效果");
            return false;
        }
    }

    // 判断是否触发冰冻效果
    public boolean freezeSpell() {
        if (Common.randomResult(freezeRate)) {
            System.out.println(Common.printSpace() + "成功触发冰冻");
            isAddDamage = true;
            return true;
        } else {
            System.out.println(Common.printSpace() + "未触发冰冻");
            return false;
        }
    }

    // 判断是否触发暴击
    public boolean catCrite() {
        if (Common.randomResult(criteRate)) {
            System.out.println(Common.printSpace() + "触发双倍伤害");
            return true;
        } else {
            System.out.println(Common.printSpace() + "小猫没有发动攻击");
            return false;
        }
    }

    // 获取技能名称
    public String getSkillName() {
        return skillName;
    }

    // 获取基础伤害
    public float getBaseDamage() {
        return baseDamage;
    }

    // 获取回血量
    public float getAddBlood() {
        return addBlood;
    }

    // 打印当前Buff状态
    public void printBuff() {
        Common.formatPrint("当前Buff状态");
        System.out.println(Common.printSpace() + "是否减伤：" + isSubDamage);
        System.out.println(Common.printSpace() + "是否加血：" + isAddBlood);
        System.out.println(Common.printSpace() + "是否冰冻：" + isFreeze);
        System.out.println(Common.printSpace() + "是否双倍伤害：" + isDoubleDamage);
        System.out.println(Common.printSpace() + "攻击是否有易伤效果加成：" + isAddDamage);
    }

    // Getter和Setter方法
    public boolean isSubDamage() {
        return isSubDamage;
    }

    public void setSubDamage(boolean subDamage) {
        isSubDamage = subDamage;
    }

    public boolean isAddBlood() {
        return isAddBlood;
    }

    public void setAddBlood(boolean addBlood) {
        isAddBlood = addBlood;
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public void setFreeze(boolean freeze) {
        isFreeze = freeze;
    }

    public boolean isDoubleDamage() {
        return isDoubleDamage;
    }

    public void setDoubleDamage(boolean doubleDamage) {
        isDoubleDamage = doubleDamage;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setBaseDamage(float baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setAddBlood(float addBlood) {
        this.addBlood = addBlood;
    }

    public void setBuffRate(float buffRate) {
        this.buffRate = buffRate;
    }

    public void setFreezeRate(float freezeRate) {
        this.freezeRate = freezeRate;
    }

    public void setCriteRate(float criteRate) {
        this.criteRate = criteRate;
    }

    public boolean isAddDamage() {
        return isAddDamage;
    }

    public void setAddDamage(boolean addDamage) {
        isAddDamage = addDamage;
    }
}
