# 数据库初始化


-- 创建库
create database if not exists my_db;

-- 切换库
use my_db;
-- 插入模拟用户数据
INSERT INTO user (userAccount, userPassword, unionId, mpOpenId, userName, userAvatar, userProfile, userRole, createTime, updateTime, isDelete)
VALUES
    ('user001', '密码123', 'wx123456', 'mp7890', '张三', 'avatar1.jpg', '这是张三的个人简介', 'user', '2023-09-21 10:00:00', '2023-09-21 10:00:00', 0),
    ('user002', 'abc@123', NULL, NULL, '李四', 'avatar2.jpg', '李四的简介内容', 'user', '2023-09-21 10:15:00', '2023-09-21 10:15:00', 0),
    ('admin001', 'adminPass', NULL, NULL, '管理员1', 'admin.jpg', '管理员1的简介', 'admin', '2023-09-21 11:30:00', '2023-09-21 11:30:00', 0),
    ('user003', '12345678', 'wx789012', NULL, '王五', 'avatar3.jpg', '王五的个人描述', 'user', '2023-09-21 12:45:00', '2023-09-21 12:45:00', 0),
    ('ban001', 'banPass', NULL, NULL, '被封禁用户', 'ban_avatar.jpg', '这是一个被封禁用户的简介', 'ban', '2023-09-21 13:00:00', '2023-09-21 13:00:00', 0);

-- 插入模拟文章数据
INSERT INTO post (title, content, tags, thumbNum, favourNum, userId, createTime, updateTime, isDelete)
VALUES
    ('如何学习编程', '学习编程是一个循序渐进的过程，需要不断练习和学习。首先，选择一门编程语言，如Python或JavaScript。然后，学习基本的语法和概念，如变量、条件语句和循环。接着，编写简单的程序来练习你的编程技能。还可以参加在线课程和编程社区，与其他程序员交流经验。最重要的是坚持不懈，不断提升自己的编程技能。', '["编程", "学习", "技巧"]', 10, 5, 1, '2023-09-21 14:00:00', '2023-09-21 14:00:00', 0),
    ('旅行日记：探索巴黎', '最近我去了巴黎，这个城市美不胜收。我参观了埃菲尔铁塔、卢浮宫和巴黎圣母院，都留下了深刻的印象。巴黎的美食也令人惊叹，我尝试了各种法国美食，包括法式奶酪和葡萄酒。总的来说，这是一次难忘的旅行，我强烈推荐大家来巴黎一游。', '["旅行", "巴黎", "美食"]', 15, 8, 2, '2023-09-21 15:30:00', '2023-09-21 15:30:00', 0),
    ('科技新闻：人工智能的未来', '人工智能领域正在取得巨大的进展。最新的研究表明，机器学习算法在医疗诊断、自动驾驶汽车和自然语言处理等领域具有巨大潜力。人工智能不仅可以提高工作效率，还可以改善生活质量。然而，也需要谨慎使用，以确保数据隐私和安全。', '["科技", "人工智能", "未来"]', 20, 12, 3, '2023-09-21 16:45:00', '2023-09-21 16:45:00', 0),
    ('美食分享：自制披萨的秘诀', '想要制作美味的披萨吗？这里有一些秘诀。首先，选择新鲜的配料，如新鲜的面团、番茄酱和奶酪。其次，调整烤箱温度和烤制时间，以确保披萨均匀烤熟。最后，尝试不同的配料组合，创造出自己喜欢的口味。制作披萨不仅有趣，还能享受美味的食物。', '["美食", "披萨", "烹饪"]', 12, 6, 4, '2023-09-21 17:15:00', '2023-09-21 17:15:00', 0),
    ('电影推荐：经典科幻电影', '如果你喜欢科幻电影，这些经典作品不容错过。包括《2001太空漫游》、《银翼杀手》和《星球大战》。这些电影不仅拥有精彩的特效和故事情节，还探讨了深刻的哲学问题。不论是科幻迷还是普通观众，都值得一看。', '["电影", "科幻", "经典"]', 18, 10, 5, '2023-09-21 18:30:00', '2023-09-21 18:30:00', 0);

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';
