# Witch Weapons - 魔女兵器

一个基于Java 21的命令行回合制战斗模拟游戏。

## 目录
- [项目概述](#项目概述)
- [功能特性](#功能特性)
- [技术栈](#技术栈)
- [项目结构](#项目结构)
- [安装与运行](#安装与运行)
- [游戏玩法](#游戏玩法)
- [开发说明](#开发说明)
- [许可证](#许可证)

## 项目概述

Witch Weapons 是一个简单的回合制战斗游戏，玩家扮演女巫与恶魔战斗。游戏包含技能系统、背包系统、商店系统和角色升级功能。

本项目旨在提供一个模块化、易于扩展的游戏开发示例，适用于学习和参考。

## 功能特性

### 核心玩法
- 回合制战斗系统：玩家与恶魔进行回合制战斗
- 技能系统：多种技能可供使用，包括治疗、冰冻等
- 背包系统：收集和使用各种道具
- 商店系统：购买道具和抽卡获取角色
- 升级系统：提升角色属性和能力

### 技能系统
- **治疗术 (HealingSpell)**: 恢复玩家生命值，有概率进入减伤状态
- **冰冻术 (Freeze)**: 冻结敌人，使其跳过回合，被冰冻的目标进入易伤状态受到双倍伤害
- **魔法猫 (MagicCat)**: 召唤魔法猫协助战斗，有概率造成双倍伤害

### 物品系统
- **草莓 (Strawberry)**: 恢复10点生命值的消耗品
- **扫帚 (Broom)**: 装饰品，使用后提高冰冻法术触发概率
- **幸运种子 (LuckySeed)**: 增加幸运值的道具，使用后战斗获胜时金币收益增加

### 商店系统
- 道具购买：使用金币购买各种道具
- 抽卡系统：抽取不同稀有度的角色卡片
    - R (普通)
    - SR (稀有)
    - SSR (超稀有)
    - UR (传说)

### 状态与机制
- **减伤状态**: 使用治疗术技能有概率进入减伤状态，降低受到的伤害
- **冰冻状态**: 使用冰冻术技能有概率冻结敌人，使其跳过回合
- **易伤状态**: 被冰冻的敌人进入易伤状态，受到的伤害翻倍
- **双倍伤害**: 使用魔法猫技能有概率造成双倍伤害
- **道具增强**: 使用扫帚道具可以提高冰冻术和魔法猫的触发概率

## 技术栈

- **语言**: Java 21
- **构建工具**: Maven
- **项目结构**: 模块化包结构设计
- **设计模式**: 策略模式、简单工厂模式、枚举模式

## 项目结构

```
com.waveraven
├── battle         // 战斗逻辑
├── common         // 常量
├── entity         // 游戏实体类
│   ├── shop       // 商店物品
├── game           // 游戏启动类
├── shop           // 商店系统
│   └── card       // 卡片相关
├── skill          // 技能系统
└── utils          // 工具类
```

### 核心类说明

- [Main.java](./src/main/java/com/waveraven/Main.java): 程序入口点
- [StartGame.java](./src/main/java/com/waveraven/game/StartGame.java): 游戏主循环和菜单系统
- [Fight.java](./src/main/java/com/waveraven/battle/Fight.java): 战斗系统实现
- [Player.java](./src/main/java/com/waveraven/entity/Player.java): 玩家实体类
- [Demon.java](./src/main/java/com/waveraven/entity/Demon.java): 恶魔实体类
- [Skill.java](./src/main/java/com/waveraven/skill/Skill.java): 技能基类
- [Backpack.java](./src/main/java/com/waveraven/entity/Backpack.java): 背包系统
- [MyShop.java](./src/main/java/com/waveraven/shop/MyShop.java): 商店系统

## 安装与运行

### 环境要求
- JDK 21
- Maven 3.x

### 构建项目
```bash
mvn clean compile
```

### 运行游戏
```bash
java -cp target/classes com.waveraven.Main
```

## 游戏玩法

### 基本流程
1. 启动游戏后进入主菜单
2. 可以选择进入商店购买道具、查看属性、升级、抽卡或开始战斗
3. 在战斗中，每个回合可以选择使用技能和背包中的物品
4. 通过战斗获得金币，用于在商店购买道具或升级属性

### 战斗系统
1. 每个回合开始时可以使用背包中的道具
2. 选择技能进行攻击：
    - 药水：恢复生命值，有概率进入减伤状态
    - 冰冻：有概率冰冻目标，被冰冻的目标无法行动且进入易伤状态
    - 魔法猫：有概率造成双倍伤害
3. 战斗胜利后获得金币奖励

### 商店系统
1. 使用金币购买道具：草莓、扫帚、幸运种子
2. 花费金币进行抽卡，获得不同稀有度的角色卡片

### 升级系统
1. 使用金币提升等级
2. 等级提升会增强各项属性：玩家血量、恶魔血量、技能效果等

## 开发说明

### 扩展性设计
项目采用模块化设计，易于扩展：

#### 添加新技能
1. 继承 [Skill](./src/main/java/com/waveraven/skill/Skill.java) 类并实现相应方法
2. 在 [Fight.selectSkill()](./src/main/java/com/waveraven/battle/Fight.java) 方法中添加新技能选项

#### 添加新物品
1. 继承 [Item](./src/main/java/com/waveraven/entity/Item.java) 类并实现 [useItem](./src/main/java/com/waveraven/entity/Item.java) 方法
2. 在 [MyShop.playerEnter()](./src/main/java/com/waveraven/shop/MyShop.java) 方法中添加新物品选项

#### 添加新敌人
1. 扩展 [Demon](./src/main/java/com/waveraven/entity/Demon.java) 类或创建新实体
2. 在 [Fight.battle()](./src/main/java/com/waveraven/battle/Fight.java) 方法中替换或添加新的敌人实例

### 设计模式
- **策略模式**：不同技能的实现
- **简单工厂模式**：对象创建
- **枚举模式**：稀有度等级管理

## 许可证

本项目仅供学习和参考使用。  
代码修改于：https://www.bilibili.com/video/BV1NMuaz6ED7/