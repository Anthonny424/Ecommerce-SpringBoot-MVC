package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Ropa;
import com.example.ProyectoIntegrador.repository.RopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RopaServiceImpl implements RopaService {

    @Autowired
    private RopaRepository repository;
    @Override
    public void saveRopa(Ropa ropa) {
          repository.save(ropa);
    }

    @Override
    public Ropa buscarRopa(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteRopa(int id) {
        repository.DeleteRopa(id);
    }

}
