# JavaFX实训————流放之地（Lands of Exile）
### 项目结构概述

这个项目是一个基于JavaFX和FXGL框架的2D冒险游戏，使用Maven作为构建工具。游戏主题为生存冒险，玩家控制角色在无限生成的地图上战斗、收集资源、升级和互动。项目分为前端（JavaFX游戏客户端）和后端（FastAPI服务，用于NPC AI对话）。前端使用FXGL处理游戏循环、实体管理和渲染；后端提供RESTful API接口，用于NPC对话生成。

- **技术栈**：
  - 前端：Java 21、JavaFX、FXGL（游戏框架，用于实体、碰撞、UI等）、Maven（依赖管理）。
  - 后端：Python FastAPI（用于AI对话服务，可能集成如OpenAI或本地模型）、langchain/langgraph、WebSocket通信协议、DeepSeek/Qwen大模型API。
  - 其他：Perlin噪声算法（用于地图生成，可用Java库如 noise4j 或手动实现）、A*怪物追逐算法。

- **整体架构**：
  - **前端模块**：
    - 入口与UI：启动页面、角色选择、游戏主界面。
    - 地图生成：无限地图，使用Perlin噪声动态生成地形和物体。
    - 实体管理：玩家、怪物、NPC、物品（水晶、金币、装备）。
    - 游戏逻辑：战斗、拾取、升级、商店、NPC交互。
    - 后端集成：HTTP客户端调用FastAPI API进行NPC对话。
  - **后端模块**：FastAPI服务，提供/chat/{npc_id} 端点，根据 npc_id 生成不同npc的AI响应，可能不同的对话结果会触发不同的事件（如奖励或受击惩罚）。
  - **依赖**：Maven pom.xml 中添加JavaFX依赖、FXGL依赖（com.github.almasb:fxgl）、HTTP客户端（如OkHttp）、Perlin噪声库。

- **运行流程**：
  1. 启动游戏，显示进入页面。
  2. 选择角色，进入游戏。
  3. 生成地图，玩家移动（WASD键），怪物生成并聚集。
  4. 自动攻击消灭怪物，拾取掉落物。
  5. 升级时弹出选择界面进行装备or属性升级。
  6. 靠近NPC/装备触发交互。
  7. 死亡结束游戏，重启或返回菜单。

### 文件结构

项目使用Maven标准结构。根目录下有pom.xml（定义依赖，如FXGL）。包名为`org.landsofexile.core`。子包按模块解藕：entities（实体）、components（组件）、api（连接外部服务）、scenc（场景）、config（常量配置）、events（事件）。resources存放资产（如图像、配置）。

详细文件树（基于src/main/java和resources）：

```
project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── landsofexile/
│   │   │           └── core/
│   │   │               ├── Main.java  // 游戏入口，继承FXGL的GameApplication，初始化游戏
│   │   │               ├── config/ // 全局配置文件，如npc、怪物、角色的属性
│   │   │               ├── entities/ // 实体，如npc、怪物、角色
│   │   │               ├── components/ // 组件，绑定到实体中实现逻辑
│   │   │               ├── api/ // 连接后端服务，实现与npc的AI式对话
│   │   │               ├── scene/ // 场景——游戏菜单页面、角色选择页面、游戏正式游玩页面
│   │   │               └── events/ // 事件处理
│   │   └── resources/
│   │       ├── assets/
│   │       │   ├── images/  // 图片资产：player.png, monster.png, tree.png 等
│   │       │   ├── sounds/  // 声音：bgm.mp3, attack.wav 等
│   │       │   └── fonts/   // 字体：gamefont.ttf
│   │       └── config/
│   │           └── game.properties  // 属性文件：如monster_base_strength=10
│   └── test/
│       └── java/  // 单元测试包，镜像main结构，如com.groupname.adventuregame.entities.PlayerTest.java
└── README.md  // 项目说明、运行指令（如mvn clean install; java -jar target/game.jar）
```

- **包设计说明**：根包`org.landsofexile.core`统一命名空间。子包按功能分组，便于import。实体用继承解藕，逻辑用接口（如ISpawnable）连接模块。

### 重难点

- **重难点分析**：
  1. **Perlin噪声地图生成（难点）**：实现无限地图需处理chunk加载/卸载，避免内存溢出。Perlin噪声算法需优化性能（用种子确保连续性），生成多样细节（如河流连贯）。建议用库noise4j，避免手动实现浮点计算错误。
  2. **怪物AI与聚集逻辑（重点）**：怪物向玩家中央聚集需路径寻找（A*算法），随等级变强（属性缩放）。FXGL的PhysicsComponent处理碰撞，但需自定义AI行为，避免性能瓶颈（多怪物时）。
  3. **后端集成与NPC对话（难点）**：前端用OkHttp异步调用FastAPI，确保低延迟。处理API错误（如网络断开用默认对话）。惊喜触发需随机逻辑，基于npc_id多态。
  4. **升级与属性系统（重点）**：经验阈值用指数增长，升级界面需暂停游戏（FXGL支持overlay）。属性提升影响实体（如攻击力），需事件广播解藕。
  5. **性能优化（难点）**：无限地图+多怪物易卡顿。用对象池复用实体，限制屏幕内渲染。测试高等级场景。
  6. **碰撞与拾取（重点）**：FXGL内置碰撞，但需自定义handler（如触怪掉血、靠近拾取）。玩家保持屏幕中央用camera跟随。
  7. **Maven构建与依赖（难点）**：确保FXGL兼容JavaFX，pom.xml中添加module-info.java（如果用模块系统）。打包成可执行JAR。

- **风险与建议**：地图生成可能耗时，用多线程。测试用JUnit覆盖逻辑。
