package com.waveraven.shop.card;

public class Character {
    private final String name;      // 角色名称
    private final String skill;     // 技能描述
    private final Rarity rarity;    // 稀有度

    public Character(String name, String skill, Rarity rarity) {
        this.name = name;
        this.skill = skill;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
