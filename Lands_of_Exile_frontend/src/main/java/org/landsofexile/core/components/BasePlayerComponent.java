package org.landsofexile.core.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import com.almasb.fxgl.dsl.FXGL;

public class BasePlayerComponent extends Component {
    /*
    * 角色基础组件
    *
    * 负责：
    * 1. 通过wasd控制角色移动
    * 2. 支持按住键持续移动
    * 3. 支持多键组合移动（如WD实现右上角移动）
    * */

    // TODO:【 后期可通过FXGL.getGameState()获取当前角色的移动速度然后进行调整，这里默认为200 】
    private double moveSpeed = 200.0; // 移动速度，像素/秒

    // 跟踪按键状态
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    @Override
    public void onAdded() {
        Input input = FXGL.getInput();

        // 向上移动 - 使用onActionBegin和onActionEnd来跟踪按键状态
        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                movingUp = true;
            }

            @Override
            protected void onActionEnd() {
                movingUp = false;
            }
        }, KeyCode.W);

        // 向下移动
        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                movingDown = true;
            }

            @Override
            protected void onActionEnd() {
                movingDown = false;
            }
        }, KeyCode.S);

        // 向左移动
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                movingLeft = true;
            }

            @Override
            protected void onActionEnd() {
                movingLeft = false;
            }
        }, KeyCode.A);

        // 向右移动
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                movingRight = true;
            }

            @Override
            protected void onActionEnd() {
                movingRight = false;
            }
        }, KeyCode.D);
    }

    @Override
    public void onUpdate(double tpf) {
        double deltaX = 0;
        double deltaY = 0;

        // 根据按键状态计算移动向量，支持多键同时按下
        if (movingUp) {
            deltaY -= moveSpeed * tpf; // 向上移动
        }
        if (movingDown) {
            deltaY += moveSpeed * tpf; // 向下移动
        }
        if (movingLeft) {
            deltaX -= moveSpeed * tpf; // 向左移动
        }
        if (movingRight) {
            deltaX += moveSpeed * tpf; // 向右移动
        }

        // 应用移动（只有在有移动时才更新位置）
        // 这样可以实现组合移动，如WD = 右上角移动
        if (deltaX != 0 || deltaY != 0) {
            entity.translate(deltaX, deltaY);
        }
    }

    // 获取移动速度
    public double getMoveSpeed() {
        return moveSpeed;
    }

    // 设置移动速度
    public void setMoveSpeed(double speed) {
        this.moveSpeed = speed;
    }
}
