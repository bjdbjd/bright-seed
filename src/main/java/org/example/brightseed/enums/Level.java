package org.example.brightseed.enums;

import lombok.Getter;

@Getter
public enum Level {
    GHOST(1, "Ghost", "亡灵"),
    NOVICE(2, "Novice", "新手"),
    BEGINNER(3, "Beginner", "初级"),
    INTERMEDIATE(4, "Intermediate", "中级"),
    ADVANCED(5, "Advanced", "高级"),
    EXPERT(6, "Expert", "专家"),
    MASTER(7, "Master", "大师"),
    CHAMPION(8, "Champion", "冠军"),
    LEGEND(9, "Legend", "传奇"),
    GODLIKE(10, "Godlike", "天神");

    private final int value;
    private final String englishName;
    private final String chineseName;

    Level(int value, String englishName, String chineseName) {
        this.value = value;
        this.englishName = englishName;
        this.chineseName = chineseName;
    }

    // 获取默认等级
    public static Level getDefaultLevel() {
        return NOVICE;
    }

    // 根据值获取等级
    public static Level fromValue(int value) {
        for (Level level : values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown level value: " + value);
    }
}
