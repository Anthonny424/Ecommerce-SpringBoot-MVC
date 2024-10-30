package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Ropa;

public interface RopaService {
    public void saveRopa(Ropa ropa);
    public Ropa buscarRopa(int id);
    public void deleteRopa(int id);
}
