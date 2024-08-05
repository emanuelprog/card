package com.presentation.card.model;

import com.presentation.card.dto.CardRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cards")
public class Card {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String bio;

    public Card(String name, Integer age, String email, String bio) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.bio = bio;
    }

    public static Card fromCardRequestDTO(CardRequestDTO cardRequestDTO, String id) {
        return new Card(id, cardRequestDTO.name(), cardRequestDTO.age(), cardRequestDTO.email(), cardRequestDTO.bio());
    }
}
