# 💬 Wild IM Backend

> 一款基于 Spring Boot 3 纯手工打磨的轻量级即时通讯（IM）系统后端。

本项目是我个人开发的 Wild IM 聊天系统后端。在实现基础架构与核心业务逻辑的同时，本项目高度注重代码的规范性与架构的整洁度。作为一个个人练手与实战项目，它聚焦于现代
Java 后端开发实践，采用清晰的分层与现代化组件，力求做到结构清晰、开箱即用，拒绝臃肿的冗余配置。

## ✨ 项目亮点

- **⚡️ 现代化技术栈**：基于 Spring Boot 3.2.5 与 Java 17 构建，享受现代化框架带来的高性能与丰富特性。
- **🛡️ 安全可靠的认证**：集成 Auth0 Java-JWT 实现无状态 Token 认证，并依托 Spring Security Crypto 提供稳固的密码加密策略。
- **📦 高效的数据持久化**：深度整合 MyBatis-Plus 3.5.17，大幅减少常规 CRUD 编写，搭配 MySQL 稳健存储。
- **🧩 优雅的工具链支持**：引入 FastJson2 高效处理 JSON 序列化，结合 Lombok 消除样板代码，保持业务逻辑的纯净。
- **🎯 纯粹的单体架构**：专注单体服务的高效与稳健，逻辑严密、模块分明，非常适合作为即时通讯与后端进阶的实战参考。

## 🛠 技术栈

- **核心框架**：[Spring Boot 3.2.5](https://spring.io/projects/spring-boot)
- **开发语言**：Java 17
- **持久层框架**：[MyBatis-Plus 3.5.17](https://baomidou.com/)
- **数据库**：MySQL 8.0+
- **安全与加密**：Java-JWT / Spring Security Crypto
- **JSON 解析**：[FastJson2](https://github.com/alibaba/fastjson2)
- **辅助工具**：Lombok / Apache Commons Lang3

## 🚀 快速开始

1. ### 克隆项目

```bash
git clone [https://github.com/h-129/wild-im-backend.git](https://github.com/h-129/wild-im-backend.git)
cd wild-im-backend