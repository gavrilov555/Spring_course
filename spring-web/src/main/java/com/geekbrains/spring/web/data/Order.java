package com.geekbrains.spring.web.data;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;
    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn (name = "client_id")
    private Client client;

}
