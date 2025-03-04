package org.example.brightseed.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@Table(name = "files")
@EqualsAndHashCode(of = {"id"})
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private String fileName; // 文件名

    private byte[] file; // 文件

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 创建时间

    // 使用@PrePersist确保在插入之前设置了createdAt
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS); // 截断到毫秒
    }
}
