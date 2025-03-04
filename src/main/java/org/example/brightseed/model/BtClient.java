package org.example.brightseed.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@Table(name = "BtClients")
@EqualsAndHashCode(of = {"id"})
public class BtClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private String peerId; // 客户端标识符（40字符十六进制）

    private Long userId; // 用户id，外键

    private String agent; // 客户端名称

    private String ipv4; // 客户端ipv4地址

    private String ipv6; // 客户端ipv6地址

    private Integer port; // 客户端端口号

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 创建时间

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 更新时间

    private boolean usable; // 是否可用

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
