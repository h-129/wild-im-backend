CREATE DATABASE IF NOT EXISTS `wild_im_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `wild_im_db`;

-- ----------------------------
-- 1. 用户表 (im_user)
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user`
(
    `id`           bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`     varchar(30)       DEFAULT NULL COMMENT '账号',
    `password`     varchar(255)      DEFAULT NULL COMMENT '密码(Spring Security BCrypt)',
    `real_name`    varchar(30)       DEFAULT NULL COMMENT '真实姓名',
    `nickname`     varchar(60)       DEFAULT NULL COMMENT '昵称',
    `sex`          tinyint(1)        DEFAULT '0' COMMENT '性别 (0:未知, 1:男, 2:女)',
    `province`     varchar(100)      DEFAULT NULL COMMENT '省',
    `city`         varchar(100)      DEFAULT NULL COMMENT '市',
    `country`      varchar(100)      DEFAULT NULL COMMENT '国家',
    `avatar`       varchar(255)      DEFAULT NULL COMMENT '头像',
    `phone_number` varchar(30)       DEFAULT NULL COMMENT '手机号',
    `id_card`      varchar(30)       DEFAULT NULL COMMENT '身份证',
    `status`       varchar(20)       DEFAULT 'DEFAULT' COMMENT '状态 (DEFAULT:正常, DISABLED:禁用)',
    `address`      varchar(255)      DEFAULT NULL COMMENT '地址',
    `is_deleted`   bigint   NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 (0:未删除, 大于0:已删除，值为id)',
    `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username_deleted` (`username`, `is_deleted`),
    UNIQUE KEY `uk_phone_deleted` (`phone_number`, `is_deleted`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表';

-- ----------------------------
-- 2. 联系人表/好友表 (im_contact)
-- ----------------------------
DROP TABLE IF EXISTS `im_contact`;
CREATE TABLE `im_contact`
(
    `id`              bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`         bigint   NOT NULL COMMENT '用户ID',
    `contact_user_id` bigint   NOT NULL COMMENT '联系人(好友)ID',
    `status`          varchar(20)       DEFAULT 'DEFAULT' COMMENT '状态 (DEFAULT:正常, BLACK:拉黑)',
    `remark`          varchar(60)       DEFAULT NULL COMMENT '联系人备注',
    `is_deleted`      bigint   NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_contact_deleted` (`user_id`, `contact_user_id`, `is_deleted`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='联系人表';

-- ----------------------------
-- 3. 会话表 (im_conversation)
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation`;
CREATE TABLE `im_conversation`
(
    `id`                bigint      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `type`              varchar(20) NOT NULL DEFAULT 'PRIVATE' COMMENT '会话类型 (PRIVATE:单聊, GROUP:群聊)',
    `latest_message_id` bigint               DEFAULT NULL COMMENT '最后一条消息ID',
    `status`            varchar(20)          DEFAULT 'DEFAULT' COMMENT '会话状态 (DEFAULT:正常)',
    `is_deleted`        bigint      NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='会话表';

-- ----------------------------
-- 4. 群组表 (im_group)
-- ----------------------------
DROP TABLE IF EXISTS `im_group`;
CREATE TABLE `im_group`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`            varchar(100) NOT NULL COMMENT '群名称',
    `create_user_id`  bigint       NOT NULL COMMENT '发起人ID',
    `status`          varchar(20)           DEFAULT 'DEFAULT' COMMENT '群状态 (DEFAULT:正常)',
    `avatar`          varchar(300)          DEFAULT NULL COMMENT '群头像',
    `notice`          varchar(1000)         DEFAULT NULL COMMENT '群公告',
    `conversation_id` bigint       NOT NULL COMMENT '关联的群聊会话ID',
    `is_deleted`      bigint       NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='群组表';

-- ----------------------------
-- 5. 用户与会话关联表 (im_user_conversation)
-- ----------------------------
DROP TABLE IF EXISTS `im_user_conversation`;
CREATE TABLE `im_user_conversation`
(
    `id`                   bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`              bigint   NOT NULL COMMENT '用户ID',
    `conversation_id`      bigint   NOT NULL COMMENT '会话ID',
    `conversation_type`    varchar(20)       DEFAULT 'PRIVATE' COMMENT '会话类型',
    `target_id`            bigint   NOT NULL COMMENT '目标ID (群组ID或对方用户ID)',
    `status`               varchar(20)       DEFAULT 'DEFAULT' COMMENT '状态',
    `remark`               varchar(100)      DEFAULT NULL COMMENT '会话备注',
    `nickname`             varchar(100)      DEFAULT NULL COMMENT '用户在群中的昵称',
    `role`                 varchar(20)       DEFAULT 'MEMBER' COMMENT '角色 (MEMBER:普通成员, ADMIN:管理员, OWNER:群主)',
    `unread_message_count` int               DEFAULT '0' COMMENT '当前会话未读消息数',
    `join_time`            datetime          DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
    `is_deleted`           bigint   NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`          datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`          datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_conversation_deleted` (`user_id`, `conversation_id`, `is_deleted`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户会话关联表';

-- ----------------------------
-- 6. 消息表 (im_message)
-- ----------------------------
DROP TABLE IF EXISTS `im_message`;
CREATE TABLE `im_message`
(
    `id`              bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `conversation_id` bigint   NOT NULL COMMENT '所属会话ID',
    `from_user_id`    bigint   NOT NULL COMMENT '发送方用户ID',
    `target_user_id`  bigint            DEFAULT '0' COMMENT '接收方用户ID(单聊)',
    `group_id`        bigint            DEFAULT '0' COMMENT '群组ID(群聊)',
    `content`         text COMMENT '文本内容',
    `type`            varchar(20)       DEFAULT 'TEXT' COMMENT '消息类型 (TEXT, IMAGE, VOICE, VIDEO, FILE, WITHDRAW等)',
    `extra`           text COMMENT '附加信息(JSON格式)',
    `duration`        int               DEFAULT NULL COMMENT '音视频时长(秒)',
    `file_name`       varchar(100)      DEFAULT NULL COMMENT '文件名',
    `file_size`       bigint            DEFAULT NULL COMMENT '文件大小(Byte)',
    `file_type`       varchar(30)       DEFAULT NULL COMMENT '文件拓展名类型',
    `file_url`        varchar(300)      DEFAULT NULL COMMENT '文件下载/访问地址',
    `thumb_url`       text COMMENT '缩略图Base64或地址',
    `is_deleted`      bigint   NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_conversation_id` (`conversation_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='聊天消息表';

-- ----------------------------
-- 7. 音视频通话记录表 (im_call)
-- ----------------------------
DROP TABLE IF EXISTS `im_call`;
CREATE TABLE `im_call`
(
    `id`           bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `from_user_id` bigint   NOT NULL COMMENT '呼叫发起方',
    `target_id`    bigint   NOT NULL COMMENT '呼叫接听方',
    `type`         varchar(20)       DEFAULT 'VOICE' COMMENT '通话类型 (VOICE:语音, VIDEO:视频)',
    `end_time`     datetime          DEFAULT NULL COMMENT '挂断/结束时间',
    `is_deleted`   bigint   NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间(呼叫时间)',
    `update_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='音视频通话记录表';

-- ----------------------------
-- 8. 系统通知与申请表 (im_system_message)
-- ----------------------------
DROP TABLE IF EXISTS `im_system_message`;
CREATE TABLE `im_system_message`
(
    `id`             bigint   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `from_user_id`   bigint            DEFAULT NULL COMMENT '发起方ID',
    `target_user_id` bigint            DEFAULT NULL COMMENT '目标用户ID',
    `group_id`       bigint            DEFAULT NULL COMMENT '相关群组ID',
    `status`         varchar(20)       DEFAULT 'PENDING' COMMENT '处理状态 (PENDING:待处理, ACCEPTED:已同意, REJECTED:已拒绝)',
    `type`           varchar(20)       DEFAULT NULL COMMENT '系统消息类型 (FRIEND_REQUEST, GROUP_INVITE等)',
    `content`        text COMMENT '消息内容或申请理由',
    `extra`          text COMMENT '附加信息(JSON格式)',
    `is_deleted`     bigint   NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='系统消息与申请表';