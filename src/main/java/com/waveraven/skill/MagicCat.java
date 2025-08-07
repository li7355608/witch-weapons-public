package com.waveraven.skill;

import com.waveraven.common.Common;

public class MagicCat extends Skill {

    // 构造函数
    public MagicCat() {
        this.skillName = "魔法猫";
        this.baseDamage = Common.CAT_DAMAGE;
        this.criteRate = Common.CATCRITE_RATE;
    }

    // 实现使用不同魔法的方法
    @Override
    public void useDifferentMagic() {
        System.out.println(Common.printSpace() + "当前选择的魔法是" + this.getSkillName());
        isDoubleDamage = this.catCrite();
    }

    @Override
    public boolean catCrite() {
        // 检查玩家是否使用了扫帚道具，如果使用了则必定触发双倍伤害
        if (player != null && player.isBroomUsed()) {
            System.out.println(Common.printSpace() + "玩家已使用扫帚道具，本回合必定触发魔法猫猫");
            return true;
        }

        if (Common.randomResult(criteRate)) {
            System.out.println(Common.printSpace() + "触发双倍伤害");
            return true;
        } else {
            System.out.println(Common.printSpace() + "小猫没有发动攻击");
            return false;
        }
    }
}
