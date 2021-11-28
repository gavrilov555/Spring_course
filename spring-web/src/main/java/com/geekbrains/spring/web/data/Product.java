package com.geekbrains.spring.web.data;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    @NotNull
    private Long id;
    @Column (name = "title")
    @NotNull
    private String title;
    @Column (name = "cost")
    @NotNull
    private Integer cost;

    @OneToMany (mappedBy = "product")
    private List<Order> orders;

}