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
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(
            targetEntity = Coach.class,
            /***
             * persist: hace insert de coach al registarr un equipo
             * remove: al eliminar el equipo, elimina el coach asociado
             * merge: si se actualiza el club, tabm actualiza al coach
             *
            */
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn( name = "id_coach")
    private Coach coach;

    @OneToMany(
            targetEntity = Player.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "club"
    )
    private List<Player> players;

    @ManyToOne(
            targetEntity = FootballAssociation.class
    )
    private FootballAssociation footballAssociation;

    @ManyToMany(
            targetEntity = FootballCompetition.class,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "club_competition",
            joinColumns = @JoinColumn(name = "club"),
            inverseJoinColumns = @JoinColumn(name = "competition")
    )
    private List<FootballCompetition> footballCompetitions;


}
