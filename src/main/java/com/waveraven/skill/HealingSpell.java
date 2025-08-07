package com.waveraven.skill;

import com.waveraven.common.Common;

public class HealingSpell extends Skill {

    // 构造函数
    public HealingSpell() {
        // 初始化技能属性
        this.skillName = "药水";
        this.addBlood = Common.ADD_BLOOD;
        this.buffRate = Common.SUBDAMAGE_BUFF_RATE;
    }

    // 实现使用不同魔法的方法
    @Override
    public void useDifferentMagic() {
        System.out.println(Common.printSpace() + "当前选择的魔法是" + this.getSkillName());
        isAddBlood = true;
        isSubDamage = this.buffPotion();
    }
}
