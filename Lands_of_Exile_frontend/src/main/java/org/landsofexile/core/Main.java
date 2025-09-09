package org.landsofexile.core;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import org.landsofexile.core.entities.EntityFactory;
import org.landsofexile.core.entities.EntityType;
import com.almasb.fxgl.dsl.FXGL;

public class Main extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Lands of Exile");
        settings.setVersion("1.0");
        settings.setWidth(1024);
        settings.setHeight(768);
        settings.setMainMenuEnabled(false);
        settings.setGameMenuEnabled(false);
    }

    @Override
    protected void initGame() {
        // 设置绿色背景
        FXGL.getGameScene().setBackgroundColor(Color.GREEN);

        // 创建玩家实体并放置在屏幕中央
        Entity player = EntityFactory.createEntity(EntityType.PLAYER_AXE_ADEPT);
        player.setPosition(FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0);

        // 将玩家实体添加到游戏世界中
        FXGL.getGameWorld().addEntity(player);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
