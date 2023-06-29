package com.djblancoh.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FootballCompetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "name", columnDefinition = "VARCHAR(300)")
    private String name;

    @Column(
            name = "quantity_price",
            length = 200,
            nullable = false
    )
    private Integer quantityPrice;

    @Column(
            name = "start_date",
            columnDefinition = "DATE"
    )
    private LocalDate startDate;
    @Column(name = "end_date",
            columnDefinition = "DATE")
    private LocalDate endDate;



}
