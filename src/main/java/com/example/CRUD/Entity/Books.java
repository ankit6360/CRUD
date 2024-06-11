package com.example.CRUD.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "books_table")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long Id;
    private String name;
    private String author;

}
