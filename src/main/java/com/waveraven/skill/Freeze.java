package com.waveraven.skill;

import com.waveraven.common.Common;

public class Freeze extends Skill {

    // 构造函数
    public Freeze() {
        this.skillName = "冰冻术";
        this.baseDamage = Common.FREEZE_DAMAGE;
        this.freezeRate = Common.FREEZE_RATE;
    }

    // 实现使用不同魔法的方法
    @Override
    public void useDifferentMagic() {
        System.out.println(Common.printSpace() + "当前选择的魔法是" + this.getSkillName());
        isFreeze = this.freezeSpell();
        // 如果触发冰冻，则进入易伤状态伤害计算
        if (isFreeze){
            isAddDamage = true;
        }
    }
    
    // 判断是否触发冰冻效果
    @Override
    public boolean freezeSpell() {
        // 检查玩家是否使用了扫帚道具，如果使用了则必定触发冰冻
        if (player != null && player.isBroomUsed()) {
            System.out.println(Common.printSpace() + "玩家已使用扫帚道具，本回合必定触发冰冻");
            return true;
        }
        
        // 正常的概率判定
        if (Common.randomResult(freezeRate)) {
            System.out.println(Common.printSpace() + "成功触发冰冻");
            return true;
        } else {
            System.out.println(Common.printSpace() + "未触发冰冻");
            return false;
        }
    }
}