package org.landsofexile.core.entities;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.landsofexile.core.components.BasePlayerComponent;

public class EntityFactory {

    /**
     * 创建实体的工厂方法
     * 适用于创建不同类型的实体（玩家、npc、怪物）
     * @param entityType 实体类型
     * @return 创建的实体
     */
    public static Entity createEntity(EntityType entityType) {
        // TODO：这个工厂创建后续可以进一步添加 view 等属性
        Entity entity = FXGL.entityBuilder()
                .type(entityType)
                .build();

        // 根据实体类型判断并绑定相应的组件和视图
        if (isPlayerType(entityType)) {
            // TEST：创建玩家的视觉表现 - 一个蓝色方块
            Rectangle playerRect = new Rectangle(32, 32, Color.BLUE);
            entity.getViewComponent().addChild(playerRect);

            // 玩家类型实体绑定BasePlayerComponent
            entity.addComponent(new BasePlayerComponent());
        } else if (isNPCType(entityType)) {
            // NPC类型实体的组件绑定逻辑
            // TODO: 后续添加NPC相关组件
        } else if (isMonsterType(entityType)) {
            // 怪物类型实体的组件绑定逻辑
            // TODO: 后续添加怪物相关组件
        }

        return entity;
    }

    /**
     * 判断是否为玩家类型
     * @param entityType 实体类型
     * @return 是否为玩家类型
     */
    private static boolean isPlayerType(EntityType entityType) {
        return entityType.name().startsWith("PLAYER");
    }

    /**
     * 判断是否为NPC类型
     * @param entityType 实体类型
     * @return 是否为NPC类型
     */
    private static boolean isNPCType(EntityType entityType) {
        return entityType.name().startsWith("NPC");
    }

    /**
     * 判断是否为怪物类型
     * @param entityType 实体类型
     * @return 是否为怪物类型
     */
    private static boolean isMonsterType(EntityType entityType) {
        return entityType.name().startsWith("MONSTER");
    }
}
