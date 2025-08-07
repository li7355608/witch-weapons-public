package com.waveraven.utils;

import com.waveraven.shop.card.Rarity;

public class ShopUtils {
    // 默认控制台颜色，记住调用后一定保证最后的输出为重置颜色
    public static final String RESET = "\u001B[0m";

    public static final String CYAN = "\u001B[36m";   // SR
    public static final String MAGENTA = "\u001B[35m"; // SSR
    public static final String YELLOW = "\u001B[33m"; // UR
    public static final String DEFAULT = "\u001B[39m"; // R

    public static String getRarityColor(Rarity rarity) {
        return switch (rarity) {
            case SR -> CYAN;
            case SSR -> MAGENTA;
            case UR -> YELLOW;
            default -> DEFAULT;
        };
    }

    // 调用此方法用于规范化输出，调用后文本颜色将转换为正常控制台颜色
    public static String resetColor() {
        return RESET;
    }
}
