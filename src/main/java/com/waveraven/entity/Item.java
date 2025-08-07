package com.waveraven.entity;


// 物品类
public abstract class Item {
    // 物品名称
    private String name;
    // 物品类型
    private ItemType type;
    // 物品价格
    private int price;
    // 物品描述
    private String description;

    // 构造函数
    public Item() {
        this.name = "默认物品";
        this.type = ItemType.DEFAULT;
        this.price = 0;
        this.description = "无描述";
    }

    // 获取物品名称
    public String getName() {
        return name;
    }

    // 获取物品类型
    public ItemType getType() {
        return type;
    }

    // 获取物品价格
    public int getPrice() {
        return price;
    }

    // 获取物品描述
    public String getDescription() {
        return description;
    }

    // 设置物品名称
    public void setName(String name) {
        this.name = name;
    }

    // 设置物品类型
    public void setType(ItemType type) {
        this.type = type;
    }

    // 设置物品价格
    public void setPrice(int price) {
        this.price = price;
    }

    // 设置物品描述
    public void setDescription(String description) {
        this.description = description;
    }

    // 使用物品（抽象方法，由子类实现）
    public abstract void useItem(Player player);
}
