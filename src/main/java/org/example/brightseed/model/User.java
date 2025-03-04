package org.example.brightseed.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Setter
@Getter
@Table(name = "users")
@EqualsAndHashCode(of = {"id"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    @Column(nullable = false)
    private String username; // 用户名

    private String email; // 邮箱

    private String password; // 密码哈希

    private String passkey; // 身份密钥

    private Long seedScore; // 做种积分

    private Long coin; // 钱币

    private Long invitation; // 请柬

    private Long actualUpload; // 实际上传

    private Long actualDownload; // 实际下载

    private Long upload; // 上传

    private Long download; // 下载

    private Long uploadTime; // 累计上传时间

    private Long downloadTime; // 累计下载时间

    private String lastLoginIp; // 最近登录IP地址

    private Set<BtClient> btClients; // 最近bt客户端

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 注册时间

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 数据更新时间

    private LocalDateTime visitAt; // 访问时间

    private boolean usable; // 是否可用

    @Transient
    private transient BCryptPasswordEncoder passwordEncoder; // 加密器

    // 使用@PrePersist和@PreUpdate确保在插入和更新之前设置了createdAt和isUsable
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS); // 截断到毫秒
        this.updatedAt = this.createdAt; // 初始化 updatedAt 与 createdAt 相同
        this.usable = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS); // 更新 updatedAt
    }
}