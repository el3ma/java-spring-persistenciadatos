package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repositorio.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private ISerieRepository repository;
    public List<SerieDTO> obtenerSeries(){
        return convierteDatos(repository.findAll());

    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerLanzamientosMasRecientes(){
        return convierteDatos(repository.lanzamientosMasRecientes());
    }

    public List<SerieDTO> convierteDatos(List<Serie> serie){
        return serie.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                        s.getGenero(), s.getActores(), s.getSinopsis()))
                .collect(Collectors.toList());
    }

    public SerieDTO obtenerPorID(Long id) {
       Optional<Serie> serie = repository.findById(id);
       if(serie.isPresent()){
           Serie s = serie.get();
           return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                   s.getGenero(), s.getActores(), s.getSinopsis());
       }
       return null;
    }
}
