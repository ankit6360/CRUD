package com.example.CRUD.Repository;

import com.example.CRUD.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books,Long> {
}
