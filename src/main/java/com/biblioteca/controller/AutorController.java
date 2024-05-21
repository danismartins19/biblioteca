package com.biblioteca.controller;

import com.biblioteca.model.Autor;
import com.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Pong!");
    }

    @GetMapping("/")
    public List<Autor> buscarTodos(){
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id){
       Autor autor = autorService.buscarPorId(id);
       return ResponseEntity.ok(autor);
    }

    @PostMapping("/cad")
    public Autor cadastrar(@RequestBody Autor autor){
        return autorService.salvar(autor);
    }

    @PatchMapping("/update/{id}")
    public Autor update(@PathVariable Long id, @RequestBody Autor autor ){
        return autorService.atualizar(id, autor);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        autorService.excluir(id);
    }

}
