package org.landsofexile.core.model;

import org.landsofexile.core.entities.EntityType;
import java.util.List;
import java.util.ArrayList;

public class BaseNPC extends BaseLife {
    /*
     * NPC基础模型
     * */

    private boolean isInteractable; // 是否可交互
    private boolean hasDialogue; // 是否有对话
//    private boolean hasShop; // 是否有商店功能
//    private boolean hasQuest; // 是否有任务功能

//    private String greeting; // 问候语
//    private List<String> dialogueOptions; // 对话选项列表

//    private String shopType; // 商店类型（武器、装备、道具、药水等）
//    private double buyPriceMultiplier; // 购买价格倍数（基于物品基础价格）
//    private double sellPriceMultiplier; // 出售价格倍数（玩家卖给NPC的价格倍数）

//    private List<String> availableQuests; // 可用任务列表
//    private List<String> completedQuests; // 已完成任务列表
//
//    private String faction; // 所属势力
//    private int relationshipLevel; // 与玩家的关系等级（影响价格和对话）

    // 构造方法
    public BaseNPC() {
        super();
//        this.dialogueOptions = new ArrayList<>();
//        this.availableQuests = new ArrayList<>();
//        this.completedQuests = new ArrayList<>();
    }

    public BaseNPC(String name, EntityType type, String description, int level,
                   double maxHP, double baseATK, double baseDef,
                   double baseDodgeRate, double baseSpeed,
                   boolean isInteractable, boolean hasDialogue) {
        super(name, type, description, level, maxHP, baseATK, baseDef, baseDodgeRate, baseSpeed);
        this.isInteractable = isInteractable;
        this.hasDialogue = hasDialogue;
//        this.hasShop = hasShop;
//        this.hasQuest = hasQuest;
//        this.greeting = greeting;
//        this.shopType = shopType;
//        this.buyPriceMultiplier = buyPriceMultiplier;
//        this.sellPriceMultiplier = sellPriceMultiplier;
//        this.faction = faction;
//        this.relationshipLevel = relationshipLevel;
//        this.dialogueOptions = new ArrayList<>();
//        this.availableQuests = new ArrayList<>();
//        this.completedQuests = new ArrayList<>();
    }

    // Getter 方法
    public boolean isInteractable() {
        return isInteractable;
    }

    public boolean hasDialogue() {
        return hasDialogue;
    }

    // Setter 方法
    public void setInteractable(boolean interactable) {
        isInteractable = interactable;
    }

    public void setHasDialogue(boolean hasDialogue) {
        this.hasDialogue = hasDialogue;
    }
}
