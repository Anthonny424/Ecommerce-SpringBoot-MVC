package com.example.ProyectoIntegrador.service;

import com.example.ProyectoIntegrador.model.Usuario;
import com.example.ProyectoIntegrador.repository.ProductoRepository;
import com.example.ProyectoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioSeviceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> ListarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> BuscarUsuario(int id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void ActualizarUsuario(Usuario usuario) {

    }

    @Override
    public void SaveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario VerificarAcceso(String email, String password) {
        return usuarioRepository.VerificarAcceso(email, password);
    }

    @Override
    public void RegistrarUsuario(String email, String password, Integer id, String rol) {
        usuarioRepository.RegistrarUsuario(email, password, id, rol);
    }

    @Override
    public Usuario VerificarAccessoAdmin(String email, String password, String rol) {
        return usuarioRepository.VerificarAccesoAdmin(email, password, rol);
    }
}
