package com.biblioteca.service;

import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.model.Autor;
import com.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public List<Autor> listarTodos(){
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id){
        Autor buscarAutor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado com o ID informado!"));
        return buscarAutor;
    }

    public Autor atualizar(Long id, Autor autorAtualizado){
        return autorRepository.findById(id).map(autor ->{
            if (autorAtualizado.getNome() != null){
                autor.setNome(autorAtualizado.getNome());
            }

            if (autorAtualizado.getBiografia() != null){
                autor.setBiografia(autorAtualizado.getBiografia());
            }

            return autorRepository.save(autor);
        }).orElseThrow(()-> new ResourceNotFoundException("Autor não encontrado!"));
    }

    public void excluir(Long id){
        Autor autor = autorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Autor não encontrado!"));
        autorRepository.delete(autor);

    }

}
