package com.waveraven.entity;

import com.waveraven.common.Common;
import com.waveraven.entity.shop.Broom;
import com.waveraven.entity.shop.LuckySeed;
import com.waveraven.entity.shop.Strawberry;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    // 存储物品的列表
    private final List<Item> items;

    // 构造函数
    public Backpack() {
        this.items = new ArrayList<>();
        // 初始化背包中的物品（示例数据）
        addItem(new Strawberry());
        addItem(new Broom());
        addItem(new LuckySeed());
    }

    // 添加物品
    public void addItem(Item item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    // 移除物品
    public boolean removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            Item currentItem = items.get(i);
            if (currentItem.getName().equals(itemName)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    // 查找物品
    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    // 选择并使用物品
    public void selectItem(String itemName, Player player) {
        Item item = findItem(itemName);
        if (item != null) {
            item.useItem(player);
            if (removeItem(itemName)) {
                System.out.println(Common.printSpace() + "成功使用物品，并已从背包中移除");
            }
        } else {
            System.out.println(Common.printSpace() + "背包中没有该物品");
        }
    }

    // 显示背包物品
    public void display() {
        System.out.println("\n===================== 背包 =====================");
        System.out.println(Common.printSpace() + "物品列表：");

        if (items.isEmpty()) {
            System.out.println(Common.printSpace() + "背包为空");
        } else {
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.println(Common.printSpace() + (i + 1) + ". " + item.getName()
                        + " - " + item.getDescription());
            }
        }
        System.out.println("=================================================");
    }
}
