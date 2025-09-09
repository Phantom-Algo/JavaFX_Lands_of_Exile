package org.landsofexile.core.model;

import org.landsofexile.core.entities.EntityType;

public class BasePlayer extends BaseLife {
    /*
    * 角色基础模型
    * */

    private double currentATK; // 当前攻击力（包含增益和减益）
    private double currentDef; // 当前防御力（包含增益和减益）
    private double currentDodgeRate; // 当前闪避率（包含增益和减益）
    private double currentSpeed; // 当前移动速度（包含增益和减益）

    private double baseCritRate; // 基础暴击率（百分比）
    private double currentCritRate; // 当前暴击率（包含增益和减益，百分比）

    private double baseCritDamage; // 暴击伤害加成（百分比）
    private double currentCritDamage; // 当前暴击伤害加成（包含增益和减益，百分比）

    private double baseAttackSpeed; // 攻击速度（影响攻击频率）
    private double currentAttackSpeed; // 当前攻击速度（包含增益和减益）

    // 构造方法
    public BasePlayer() {
        super();
    }

    public BasePlayer(String name, EntityType type, String description, int level,
                     double maxHP, double baseATK, double baseDef,
                     double baseDodgeRate, double baseSpeed,
                     double baseCritRate, double baseCritDamage,
                     double baseAttackSpeed) {
        super(name, type, description, level, maxHP, baseATK, baseDef, baseDodgeRate, baseSpeed);
        this.currentATK = baseATK;
        this.currentDef = baseDef;
        this.currentDodgeRate = baseDodgeRate;
        this.currentSpeed = baseSpeed;
        this.baseCritRate = baseCritRate;
        this.currentCritRate = baseCritRate;
        this.baseCritDamage = baseCritDamage;
        this.currentCritDamage = baseCritDamage;
        this.baseAttackSpeed = baseAttackSpeed;
        this.currentAttackSpeed = baseAttackSpeed;
    }

    // Getter 方法
    public double getCurrentATK() {
        return currentATK;
    }

    public double getCurrentDef() {
        return currentDef;
    }

    public double getCurrentDodgeRate() {
        return currentDodgeRate;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getBaseCritRate() {
        return baseCritRate;
    }

    public double getCurrentCritRate() {
        return currentCritRate;
    }

    public double getBaseCritDamage() {
        return baseCritDamage;
    }

    public double getCurrentCritDamage() {
        return currentCritDamage;
    }

    public double getBaseAttackSpeed() {
        return baseAttackSpeed;
    }

    public double getCurrentAttackSpeed() {
        return currentAttackSpeed;
    }

    // Setter 方法
    public void setCurrentATK(double currentATK) {
        this.currentATK = currentATK;
    }

    public void setCurrentDef(double currentDef) {
        this.currentDef = currentDef;
    }

    public void setCurrentDodgeRate(double currentDodgeRate) {
        this.currentDodgeRate = currentDodgeRate;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setBaseCritRate(double baseCritRate) {
        this.baseCritRate = baseCritRate;
    }

    public void setCurrentCritRate(double currentCritRate) {
        this.currentCritRate = currentCritRate;
    }

    public void setBaseCritDamage(double baseCritDamage) {
        this.baseCritDamage = baseCritDamage;
    }

    public void setCurrentCritDamage(double currentCritDamage) {
        this.currentCritDamage = currentCritDamage;
    }

    public void setBaseAttackSpeed(double baseAttackSpeed) {
        this.baseAttackSpeed = baseAttackSpeed;
    }

    public void setCurrentAttackSpeed(double currentAttackSpeed) {
        this.currentAttackSpeed = currentAttackSpeed;
    }

    // 重置所有当前值为基础值
    public void resetToBaseStats() {
        this.currentATK = getBaseATK();
        this.currentDef = getBaseDef();
        this.currentDodgeRate = getBaseDodgeRate();
        this.currentSpeed = getBaseSpeed();
        this.currentCritRate = this.baseCritRate;
        this.currentCritDamage = this.baseCritDamage;
        this.currentAttackSpeed = this.baseAttackSpeed;
    }
}
