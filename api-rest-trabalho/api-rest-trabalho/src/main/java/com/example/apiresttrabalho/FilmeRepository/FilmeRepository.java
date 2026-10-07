package com.example.apiresttrabalho.repository;

import com.example.apiresttrabalho.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {


    @Query("""
            SELECT f
            FROM Filme f
            WHERE (:genero IS NULL OR LOWER(f.genero) = LOWER(:genero))
              AND (:ano IS NULL OR f.ano = :ano)
              AND (:avaliacaoMinima IS NULL OR f.avaliacao >= :avaliacaoMinima)
            """)
    List<Filme> filtrar(
            @Param("genero") String genero,
            @Param("ano") Integer ano,
            @Param("avaliacaoMinima") Double avaliacaoMinima
    );
}