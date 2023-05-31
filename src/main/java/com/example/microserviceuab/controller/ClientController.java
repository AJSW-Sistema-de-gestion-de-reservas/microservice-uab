package com.example.microserviceuab.controller;

import com.example.microserviceuab.dto.ClientCreationRequestDto;
import com.example.microserviceuab.dto.ClientInfoResponseDto;
import com.example.microserviceuab.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody ClientCreationRequestDto dto) {
        service.register(dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ClientInfoResponseDto> getInfoFromId(@PathVariable String id) {
        ClientInfoResponseDto result = service.getInfoFromId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("chatId/{chatId}")
    public ResponseEntity<ClientInfoResponseDto> getInfoFromChatId(@PathVariable long chatId) {
        ClientInfoResponseDto result = service.getInfoFromChatId(chatId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<ClientInfoResponseDto> getInfoFromUsername(@PathVariable String username) {
        ClientInfoResponseDto result = service.getInfoFromUsername(username);
        return ResponseEntity.ok(result);
    }

}