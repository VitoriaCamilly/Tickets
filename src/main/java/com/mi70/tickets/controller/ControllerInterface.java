package com.mi70.tickets.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ControllerInterface<DTO,ID> {

    @PostMapping
    ResponseEntity<?> create(@RequestBody DTO dto);
    @GetMapping("/{id}")
    ResponseEntity<?> readOne(@PathVariable ID id);
    @GetMapping
    ResponseEntity<?> readAll();
    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable ID id, @RequestBody DTO dto);
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable ID id);
}
