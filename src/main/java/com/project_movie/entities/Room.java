package com.project_movie.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int capacity;
    private double totalArea;

    @Column(length = 1000)
    private String imgURL;

    @ManyToOne
    @JoinColumn(nullable = false,name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cinema cinema;
}
