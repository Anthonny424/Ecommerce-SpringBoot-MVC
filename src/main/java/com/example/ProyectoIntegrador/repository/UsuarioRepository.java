package com.example.ProyectoIntegrador.repository;

import com.example.ProyectoIntegrador.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuarios WHERE email like :email AND aes_decrypt(pass, 'clave123') like :password", nativeQuery = true)
    Usuario VerificarAcceso(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM usuarios WHERE email like :email AND aes_decrypt(pass, 'clave123') like :password AND rol like :rol", nativeQuery = true)
    Usuario VerificarAccesoAdmin(@Param("email") String email, @Param("password") String password, @Param("rol")String rol);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usuarios VALUES (null, :email, aes_encrypt(:pass,'clave123'),:rol ,:foranea)" , nativeQuery = true)
    public void RegistrarUsuario(@Param("email") String email, @Param("pass") String password, @Param("foranea") Integer foranea, @Param("rol")String rol);
}
