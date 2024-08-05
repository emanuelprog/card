package com.presentation.card.service;

import com.presentation.card.dto.CardRequestDTO;
import com.presentation.card.dto.CardResponseDTO;
import com.presentation.card.exception.BadRequestException;
import com.presentation.card.exception.NotFoundException;
import com.presentation.card.model.Card;
import com.presentation.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public List<CardResponseDTO> find() {
        return cardRepository.findAll().stream().map(card -> CardResponseDTO.fromCard(card)).collect(Collectors.toList());
    }

    public CardResponseDTO save(CardRequestDTO cardRequestDTO) {
        try {
            Card card = cardRepository.save(Card.fromCardRequestDTO(cardRequestDTO, null));
            return CardResponseDTO.fromCard(card);
        } catch (Exception e) {
            throw new BadRequestException("Failed to save the card.");
        }
    }

    public CardResponseDTO edit(String id, CardRequestDTO cardRequestDTO) {
        Optional<Card> cardDB = cardRepository.findById(id);

        if (!cardDB.isPresent()) {
            throw new NotFoundException("Card not found.");
        }

        try {
            Card updatedCard = cardRepository.save(Card.fromCardRequestDTO(cardRequestDTO, id));
            return CardResponseDTO.fromCard(updatedCard);
        } catch (Exception e) {
            throw new BadRequestException("Failed to edit the card.");
        }
    }

    public String remove(String id) {
        Optional<Card> cardDB = cardRepository.findById(id);

        if (!cardDB.isPresent()) {
            throw new NotFoundException("Card not found.");
        }

        try {
            cardRepository.deleteById(id);
            return "Card delete success!";
        } catch (Exception e) {
            throw new BadRequestException("Unable to delete card.");
        }
    }
}
