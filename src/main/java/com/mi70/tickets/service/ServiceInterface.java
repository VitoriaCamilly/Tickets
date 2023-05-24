package com.mi70.tickets.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceInterface<Entity,DTO,ID> {
    Entity create(DTO dto);
    Entity readOne(ID id);
    List<Entity> readAll();
    void update(Entity entity);
    void delete(ID id);
}
