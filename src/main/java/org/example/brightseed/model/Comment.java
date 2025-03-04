package org.example.brightseed.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@Table(name = "comments")
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private Long userId;

    private String comment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 创建时间

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 更新时间

    // 使用@PrePersist和@PreUpdate确保在插入和更新之前设置了createdAt和isUsable
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS); // 截断到毫秒
        this.updatedAt = this.createdAt; // 初始化 updatedAt 与 createdAt 相同
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS); // 更新 updatedAt
    }
}
