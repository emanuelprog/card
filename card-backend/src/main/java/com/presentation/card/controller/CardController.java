package com.presentation.card.controller;

import com.presentation.card.dto.CardRequestDTO;
import com.presentation.card.dto.CardResponseDTO;
import com.presentation.card.model.Card;
import com.presentation.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.find());
    }

    @PostMapping
    public ResponseEntity<CardResponseDTO> create(@RequestBody CardRequestDTO cardRequestDTO) {
       return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(cardRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDTO> update(@PathVariable String id, @RequestBody CardRequestDTO cardRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.edit(id, cardRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.remove(id));
    }
}
