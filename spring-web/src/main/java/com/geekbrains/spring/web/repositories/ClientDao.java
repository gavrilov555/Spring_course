package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Client;

import java.util.List;

public interface ClientDao {

    Client findById(Long id);
    Client findByName(String name);
    List<Client> findAll();
    void save (Client client);
}
