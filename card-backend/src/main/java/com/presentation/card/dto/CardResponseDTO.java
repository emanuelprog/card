package com.presentation.card.dto;

import com.presentation.card.model.Card;

public record CardResponseDTO(String id, String name, Integer age, String email, String bio) {
    public static CardResponseDTO fromCard(Card card) {
        return new CardResponseDTO(card.getId(), card.getName(), card.getAge(), card.getEmail(), card.getBio());
    }
}
