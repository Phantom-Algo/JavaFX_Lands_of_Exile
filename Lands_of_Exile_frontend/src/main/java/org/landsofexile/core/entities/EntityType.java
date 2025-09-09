package org.landsofexile.core.entities;

/*
* EntityType枚举类范式
* 字段必须都是大写字母
* 字段之间用下划线分隔
*
* 关于角色的EntityType
* 开头必须是PLAYER
*
* 关于NPC的EntityType
* 开头必须是NPC，最后必须是NPC_ID（目前00001，00002不能使用）
*
* 关于怪物的EntityType
* 开头必须是MONSTER
* */

public enum EntityType {
    // 角色
    PLAYER_AXE_ADEPT, // 斧术师

    // NPC
    NPC_YEFAN_00002, // 叶凡

    // 怪物
    MONSTER_SKELETON // 骷髅怪
}
