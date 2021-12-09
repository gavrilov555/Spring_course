package com.geekbrains.spring.web.data;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    @NonNull
    private Long id;
    @Column (name = "title")
    @NonNull
    private String title;
    @Column (name = "cost")
    @NonNull
    private Integer cost;

}