package org.example.brightseed.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Table(name = "torrents")
@EqualsAndHashCode(of = {"id"})
public class Torrent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private String mainTitle; // 主标题

    private String secondTitle; // 副标题

    private String doubanId; // 豆瓣ID

    private String imdbId; // IMDb ID

    private String mediaInfo; // MediaInfo

    private String description; // 简介

    private Double size; // 种子大小

    private Long typeId; // 种子类型ID

    private Long sourceId; // 来源ID

    private Long mediaId; // 媒介ID

    private Long resolutionId; // 分辨率ID

    private Long videoCodecId; // 视频编码ID

    private Long audioCodecId; // 音频编码ID

    private Long regionId; // 地区ID

    private Long teamId; // 制作组ID

    private Long publisherId; // 发布者ID

    private Set<Long> seederIds = new HashSet<>(); // 做种者id

    private Set<Long> leecherIds = new HashSet<>(); // 下载者id

    private List<Comment> comments = new ArrayList<>(); // 评论区

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
