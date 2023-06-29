package com.djblancoh.jpa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FootballAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String ceo;

    @OneToMany(
            targetEntity = Club.class,
            fetch = FetchType.LAZY,
            mappedBy = "footballAssociation"
    )
    private List<Club> clubs;
}
