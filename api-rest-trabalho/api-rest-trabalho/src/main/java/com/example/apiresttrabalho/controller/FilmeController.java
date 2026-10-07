package com.example.apiresttrabalho.controller;

import com.example.apiresttrabalho.model.Filme;
import com.example.apiresttrabalho.repository.FilmeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }



    @GetMapping
    public ResponseEntity<List<Filme>> listarTodos() {

        return ResponseEntity.ok(
                filmeRepository.findAll()
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @PathVariable Long id) {

        Optional<Filme> filme =
                filmeRepository.findById(id);

        if (filme.isPresent()) {

            return ResponseEntity.ok(
                    filme.get()
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Filme não encontrado.");
    }


    @GetMapping("/filtros")
    public ResponseEntity<List<Filme>> filtrar(

            @RequestParam(required = false)
            String genero,

            @RequestParam(required = false)
            Integer ano,

            @RequestParam(required = false)
            Double avaliacaoMinima) {

        List<Filme> resultado =
                filmeRepository.filtrar(
                        genero,
                        ano,
                        avaliacaoMinima
                );

        return ResponseEntity.ok(resultado);
    }



    @PostMapping
    public ResponseEntity<Filme> cadastrar(
            @RequestBody Filme filme) {


        filme.setId(null);

        Filme filmeSalvo =
                filmeRepository.save(filme);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(filmeSalvo);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Filme filmeAtualizado) {

        Optional<Filme> resultado =
                filmeRepository.findById(id);

        if (resultado.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Filme não encontrado.");
        }

        Filme filme = resultado.get();

        filme.setTitulo(
                filmeAtualizado.getTitulo()
        );

        filme.setGenero(
                filmeAtualizado.getGenero()
        );

        filme.setAno(
                filmeAtualizado.getAno()
        );

        filme.setDiretor(
                filmeAtualizado.getDiretor()
        );

        filme.setDuracao(
                filmeAtualizado.getDuracao()
        );

        filme.setClassificacao(
                filmeAtualizado.getClassificacao()
        );

        filme.setPlataforma(
                filmeAtualizado.getPlataforma()
        );

        filme.setAvaliacao(
                filmeAtualizado.getAvaliacao()
        );

        filme.setDisponivel(
                filmeAtualizado.getDisponivel()
        );

        Filme atualizado =
                filmeRepository.save(filme);

        return ResponseEntity.ok(atualizado);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable Long id) {

        if (!filmeRepository.existsById(id)) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Filme não encontrado.");
        }

        filmeRepository.deleteById(id);

        return ResponseEntity.ok(
                "Filme excluído com sucesso."
        );
    }
}