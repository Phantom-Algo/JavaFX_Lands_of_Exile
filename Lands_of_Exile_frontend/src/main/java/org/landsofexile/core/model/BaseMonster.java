package org.landsofexile.core.model;

import org.landsofexile.core.entities.EntityType;

public class BaseMonster extends BaseLife {
    /*
    * 怪物基础模型
    * */

    private double currentDef; // 当前防御力（包含增益和减益）
    private double currentDodgeRate; // 当前闪避率（包含增益和减益）
    private double currentSpeed; // 当前移动速度（包含增益和减益）

    private double baseAttackSpeed; // 攻击速度（影响攻击频率）
    private double currentAttackSpeed; // 当前攻击速度（包含增益和减益）

    private double detectionRange; // 侦测范围（发现敌人的距离）
    private double attackRange; // 攻击范围

    private boolean isAggressive; // 是否主动攻击
    private boolean isBoss; // 是否为Boss怪物

    private double expReward; // 击杀后获得的经验值奖励
    private double goldReward; // 击杀后获得的金币奖励

    // 构造方法
    public BaseMonster() {
        super();
    }

    public BaseMonster(String name, EntityType type, String description, int level,
                      double maxHP, double baseATK, double baseDef,
                      double baseDodgeRate, double baseSpeed, double baseAttackSpeed,
                      double detectionRange, double attackRange,
                      boolean isAggressive, boolean isBoss,
                      double expReward, double goldReward) {
        super(name, type, description, level, maxHP, baseATK, baseDef, baseDodgeRate, baseSpeed);
        this.currentDef = baseDef;
        this.currentDodgeRate = baseDodgeRate;
        this.currentSpeed = baseSpeed;
        this.baseAttackSpeed = baseAttackSpeed;
        this.currentAttackSpeed = baseAttackSpeed;
        this.detectionRange = detectionRange;
        this.attackRange = attackRange;
        this.isAggressive = isAggressive;
        this.isBoss = isBoss;
        this.expReward = expReward;
        this.goldReward = goldReward;
    }

    // Getter 方法
    public double getCurrentDef() {
        return currentDef;
    }

    public double getCurrentDodgeRate() {
        return currentDodgeRate;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getBaseAttackSpeed() {
        return baseAttackSpeed;
    }

    public double getCurrentAttackSpeed() {
        return currentAttackSpeed;
    }

    public double getDetectionRange() {
        return detectionRange;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public boolean isAggressive() {
        return isAggressive;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public double getExpReward() {
        return expReward;
    }

    public double getGoldReward() {
        return goldReward;
    }

    // Setter 方法
    public void setCurrentDef(double currentDef) {
        this.currentDef = currentDef;
    }

    public void setCurrentDodgeRate(double currentDodgeRate) {
        this.currentDodgeRate = currentDodgeRate;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setBaseAttackSpeed(double baseAttackSpeed) {
        this.baseAttackSpeed = baseAttackSpeed;
    }

    public void setCurrentAttackSpeed(double currentAttackSpeed) {
        this.currentAttackSpeed = currentAttackSpeed;
    }

    public void setDetectionRange(double detectionRange) {
        this.detectionRange = detectionRange;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }

    public void setAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public void setExpReward(double expReward) {
        this.expReward = expReward;
    }

    public void setGoldReward(double goldReward) {
        this.goldReward = goldReward;
    }

    // 重置所有当前值为基础值
    public void resetToBaseStats() {
        this.currentDef = getBaseDef();
        this.currentDodgeRate = getBaseDodgeRate();
        this.currentSpeed = getBaseSpeed();
        this.currentAttackSpeed = this.baseAttackSpeed;
    }

    // 计算击杀奖励（可以根据等级差异调整）
    public double calculateExpReward(int playerLevel) {
        double levelDiff = this.getLevel() - playerLevel;
        double multiplier = 1.0 + (levelDiff * 0.1); // 等级差每级增加10%奖励
        return Math.max(0.1, expReward * Math.max(0.1, multiplier)); // 至少10%奖励
    }

    public double calculateGoldReward(int playerLevel) {
        double levelDiff = this.getLevel() - playerLevel;
        double multiplier = 1.0 + (levelDiff * 0.05); // 等级差每级增加5%奖励
        return Math.max(0.1, goldReward * Math.max(0.1, multiplier)); // 至少10%奖励
    }
}
