package com.github.akizminet.botman.domain.meal;

import com.github.akizminet.botman.domain.telegram.InlineKeyboardButton;

public enum MealButton {
    BREAKFAST,
    LUNCH,
    DINNER,
    CONFIRM,
    EDIT;

    @Override
    public String toString() {
        return switch (this) {
            case BREAKFAST -> "Sáng";
            case LUNCH -> "Trưa";
            case DINNER -> "Tối";
            case CONFIRM -> "Chốt";
            case EDIT -> "Sửa";
        };
    }

    public InlineKeyboardButton getInlineKeyboardButton(Boolean activated) {
        if (this == EDIT) return new InlineKeyboardButton("Sửa", this.name());
        if (this == CONFIRM) return new InlineKeyboardButton("Chốt", this.name());
        var activatedText = activated ? String.format("✅ %s", this.toString()) : this.toString();
        return new InlineKeyboardButton(activatedText, this.name());
    }
}
