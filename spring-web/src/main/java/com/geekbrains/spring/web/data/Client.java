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
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    @NotNull
    private Long id;
    @Column (name = "name")
    @NotNull
    private String name;

    @OneToMany (mappedBy = "client")
    private List<Order> orders;
}
