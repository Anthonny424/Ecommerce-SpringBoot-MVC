package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {
    public List<Usuario> ListarUsuarios();
    public Optional<Usuario> BuscarUsuario(int id);
    public void ActualizarUsuario(Usuario usuario);
    public void SaveUsuario(Usuario usuario);
    public Usuario VerificarAcceso(String email, String password);
    //public boolean Existe (String email, String password);

    public void RegistrarUsuario(String email, String password, Integer id, String rol);

    public Usuario VerificarAccessoAdmin(String email, String password, String rol);


}
