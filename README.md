# JavaFX实训————流放之地（Lands of Exile）
### 项目结构概述

这个项目是一个基于JavaFX和FXGL框架的2D冒险游戏，使用Maven作为构建工具。游戏主题为生存冒险，玩家控制角色在无限生成的地图上战斗、收集资源、升级和互动。项目分为前端（Java游戏客户端）和后端（FastAPI服务，用于NPC AI对话）。前端使用FXGL处理游戏循环、实体管理和渲染；后端提供RESTful API接口，用于NPC对话生成。

- **技术栈**：
  - 前端：Java 21+、JavaFX（集成在FXGL中）、FXGL（游戏框架，用于实体、碰撞、UI等）、Maven（依赖管理）。
  - 后端：Python FastAPI（用于AI对话服务，可能集成如OpenAI或本地模型）。
  - 其他：Perlin噪声算法（用于地图生成，可用Java库如 noise4j 或手动实现）。

- **整体架构**：
  - **模块化设计**：遵循MVC（Model-View-Controller）模式解藕。Model处理数据（如玩家状态、地图数据）；View处理UI渲染；Controller处理逻辑（如输入、碰撞）。
  - **前端模块**：
    - 入口与UI：启动页面、角色选择、游戏主界面。
    - 地图生成：无限地图，使用Perlin噪声动态生成地形和物体。
    - 实体管理：玩家、怪物、NPC、物品（水晶、金币、装备）。
    - 游戏逻辑：战斗、拾取、升级、商店、NPC交互。
    - 后端集成：HTTP客户端调用FastAPI API进行NPC对话。
  - **后端模块**：FastAPI服务，提供/npc/dialog endpoint，根据npc_id生成AI响应，可能触发事件（如奖励）。
  - **依赖**：Maven pom.xml 中添加FXGL依赖（com.github.almasb:fxgl）、HTTP客户端（如OkHttp）、Perlin噪声库。

- **运行流程**：
  1. 启动游戏，显示进入页面。
  2. 选择角色，进入游戏。
  3. 生成地图，玩家移动（WASD键），怪物生成并聚集。
  4. 自动攻击消灭怪物，拾取掉落物。
  5. 升级时弹出选择界面。
  6. 靠近商店/NPC/装备触发交互。
  7. 死亡结束游戏，重启或返回菜单。

### 分工安排

小组5人，遵循解藕原则：每个成员负责独立模块，模块间通过接口（如抽象类或事件监听）交互，减少依赖。两人为主力（假设A和B为主力，A负责后端FastAPI）。分工基于模块独立性，确保一人一类工作（如一人全管地图，一人全管实体），影响最小化。主力负责复杂模块，其他人可辅助测试/集成。

1. **成员1 (主力A)**：负责全部后端FastAPI工作。包括API设计、实现NPC对话逻辑（集成AI模型）、根据npc_id生成响应、触发惊喜事件（如随机奖励数据返回）。输出：独立FastAPI项目，可本地部署。影响：仅前端调用API，无需改动前端代码。
   
2. **成员2 (主力B)**：负责游戏入口与UI模块。包括进入页面、角色选择界面、游戏主界面、升级选择界面、商店购买界面。对接FXGL的UI组件。输出：UI类和场景管理。影响：提供UI接口给其他模块（如地图渲染到主界面）。

3. **成员3**：负责地图生成模块。包括Perlin噪声算法实现、无限地图动态生成（树木、建筑、河流、花草）、随机刷新商店/NPC/装备。输出：地图实体和生成器类。影响：地图数据通过接口提供给实体管理模块，无需关心玩家/怪物逻辑。

4. **成员4**：负责实体与战斗逻辑模块。包括玩家控制（移动、自动攻击、掉血死亡）、怪物生成/聚集/变强、碰撞检测、掉落物（水晶、金币）拾取。输出：实体类和逻辑控制器。影响：实体通过FXGL组件注册到游戏世界，与地图/UI解藕。

5. **成员5**：负责交互与升级模块。包括经验升级逻辑、属性/装备选择、NPC对话（调用后端API）、商店购买、惊喜触发。输出：交互事件和状态管理类。影响：监听实体事件（如靠近NPC），不直接修改地图或UI。

- **协作流程**：每周开会集成测试。使用Git分支开发（main为主分支，每人feature分支）。主力A/B负责整体架构指导和代码审查。测试：成员间交叉单元测试，集成时用Mock API模拟后端。

### 文件结构

项目使用Maven标准结构。根目录下有pom.xml（定义依赖，如FXGL）。包名为`com.groupname.adventuregame`（假设组名为groupname，可替换）。子包按模块解藕：entities（实体）、map（地图）、ui（界面）、logic（逻辑）、network（后端集成）。resources存放资产（如图像、配置）。

详细文件树（基于src/main/java和resources）：

```
project-root/
├── pom.xml  // Maven配置文件，添加依赖如<dependencies><dependency><groupId>com.github.almasb</groupId><artifactId>fxgl</artifactId><version>17.3</version></dependency></dependencies>
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── groupname/
│   │   │           └── adventuregame/
│   │   │               ├── Main.java  // 游戏入口，继承FXGL的GameApplication，初始化游戏
│   │   │               ├── config/
│   │   │               │   ├── GameConfig.java  // 常量配置，如地图大小、怪物强度阈值
│   │   │               │   └── NpcConfig.java  // NPC类型配置，根据npc_id映射种类
│   │   │               ├── entities/
│   │   │               │   ├── Player.java  // 玩家实体：继承Entity，处理移动、血量、经验
│   │   │               │   ├── Monster.java  // 怪物实体：继承Entity，AI向玩家聚集、变强逻辑
│   │   │               │   ├── Npc.java  // NPC实体：继承Entity，根据id类型，对话触发
│   │   │               │   ├── Shop.java  // 商店实体：继承Entity，购买逻辑
│   │   │               │   ├── Item.java  // 物品基类：水晶、金币、装备
│   │   │               │   ├── Crystal.java  // 水晶：继承Item，增加经验
│   │   │               │   ├── Coin.java  // 金币：继承Item，用于购买
│   │   │               │   └── Equipment.java  // 装备：继承Item，提升属性
│   │   │               ├── map/
│   │   │               │   ├── PerlinNoiseGenerator.java  // Perlin噪声实现，生成地图细节
│   │   │               │   ├── InfiniteMap.java  // 无限地图管理：动态加载chunk
│   │   │               │   ├── TerrainTile.java  // 地形瓦片：树木、河流等
│   │   │               │   └── MapSpawner.java  // 随机刷新商店/NPC/装备
│   │   │               ├── ui/
│   │   │               │   ├── StartScreen.java  // 进入页面：按钮进入游戏
│   │   │               │   ├── CharacterSelectScreen.java  // 角色选择界面
│   │   │               │   ├── GameHud.java  // 游戏HUD：显示血量、经验、金币
│   │   │               │   ├── UpgradeScreen.java  // 升级选择：装备/属性面板
│   │   │               │   ├── ShopScreen.java  // 商店购买界面
│   │   │               │   └── DialogScreen.java  // NPC对话界面：显示AI响应
│   │   │               ├── logic/
│   │   │               │   ├── GameController.java  // 主控制器：处理输入、更新循环
│   │   │               │   ├── CombatSystem.java  // 战斗逻辑：自动攻击、碰撞掉血
│   │   │               │   ├── LevelSystem.java  // 升级逻辑：经验阈值、属性提升
│   │   │               │   └── SpawnSystem.java  // 怪物生成：随等级变强/变多
│   │   │               └── network/
│   │   │                   └── ApiClient.java  // HTTP客户端：调用FastAPI，如post /npc/dialog {npc_id, player_input}
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

- **包设计说明**：根包`com.groupname.adventuregame`统一命名空间。子包按功能分组，便于import。实体用继承解藕，逻辑用接口（如ISpawnable）连接模块。
- **后端文件结构**（独立项目，由成员1负责）：
  ```
  backend-root/
  ├── main.py  // FastAPI app启动
  ├── requirements.txt  // 依赖：fastapi, uvicorn, openai (如果用)
  ├── models/
  │   └── npc.py  // NPC响应模型
  └── routers/
      └── dialog.py  // API路由：@app.post("/npc/dialog")
  ```

### 重难点

- **重难点分析**：
  1. **Perlin噪声地图生成（难点）**：实现无限地图需处理chunk加载/卸载，避免内存溢出。Perlin噪声算法需优化性能（用种子确保连续性），生成多样细节（如河流连贯）。建议用库noise4j，避免手动实现浮点计算错误。
  2. **怪物AI与聚集逻辑（重点）**：怪物向玩家中央聚集需路径寻找（A*算法），随等级变强（属性缩放）。FXGL的PhysicsComponent处理碰撞，但需自定义AI行为，避免性能瓶颈（多怪物时）。
  3. **后端集成与NPC对话（难点）**：前端用OkHttp异步调用FastAPI，确保低延迟。处理API错误（如网络断开用默认对话）。惊喜触发需随机逻辑，基于npc_id多态。
  4. **升级与属性系统（重点）**：经验阈值用指数增长，升级界面需暂停游戏（FXGL支持overlay）。属性提升影响实体（如攻击力），需事件广播解藕。
  5. **性能优化（难点）**：无限地图+多怪物易卡顿。用对象池复用实体，限制屏幕内渲染。测试高等级场景。
  6. **碰撞与拾取（重点）**：FXGL内置碰撞，但需自定义handler（如触怪掉血、靠近拾取）。玩家保持屏幕中央用camera跟随。
  7. **Maven构建与依赖（难点）**：确保FXGL兼容JavaFX，pom.xml中添加module-info.java（如果用模块系统）。打包成可执行JAR。

- **风险与建议**：地图生成可能耗时，用多线程。测试用JUnit覆盖逻辑。明天检查时，展示pom.xml和包结构，强调解藕。
