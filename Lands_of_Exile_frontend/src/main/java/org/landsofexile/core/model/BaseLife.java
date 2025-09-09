package org.landsofexile.core.model;

import org.landsofexile.core.entities.EntityType;

public class BaseLife {

    private String name;
    private EntityType type; // 类型
    private String description; // 描述
    private int level; // 等级

    private double maxHP; // 最大生命值
    private double currentHP; // 当前生命值

    private double baseATK; // 基础攻击力
    private double baseDef; // 基础防御力
    private double baseDodgeRate; // 基础闪避率
    private double baseSpeed; // 基础移动速度

    // 构造方法
    public BaseLife() {
    }

    public BaseLife(String name, EntityType type, String description, int level,
                   double maxHP, double baseATK, double baseDef,
                   double baseDodgeRate, double baseSpeed) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.level = level;
        this.maxHP = maxHP;
        this.currentHP = maxHP; // 初始时当前生命值等于最大生命值
        this.baseATK = baseATK;
        this.baseDef = baseDef;
        this.baseDodgeRate = baseDodgeRate;
        this.baseSpeed = baseSpeed;
    }

    // Getter 方法
    public String getName() {
        return name;
    }

    public EntityType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public double getBaseATK() {
        return baseATK;
    }

    public double getBaseDef() {
        return baseDef;
    }

    public double getBaseDodgeRate() {
        return baseDodgeRate;
    }

    public double getBaseSpeed() {
        return baseSpeed;
    }

    // Setter 方法

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentHP(double currentHP) {
        this.currentHP = Math.max(0, Math.min(currentHP, maxHP)); // 确保当前生命值在0和最大生命值之间
    }

    public void setBaseATK(double baseATK) {
        this.baseATK = baseATK;
    }

    public void setBaseDef(double baseDef) {
        this.baseDef = baseDef;
    }

    public void setBaseDodgeRate(double baseDodgeRate) {
        this.baseDodgeRate = baseDodgeRate;
    }

    public void setBaseSpeed(double baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    // 实用方法
    public boolean isAlive() {
        return currentHP > 0;
    }

    public double getHPPercentage() {
        return maxHP > 0 ? (currentHP / maxHP) * 100 : 0;
    }

    public void heal(double amount) {
        setCurrentHP(currentHP + amount);
    }

    public void takeDamage(double damage) {
        setCurrentHP(currentHP - damage);
    }
}
