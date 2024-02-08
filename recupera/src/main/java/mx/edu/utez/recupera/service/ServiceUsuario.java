package mx.edu.utez.recupera.service;

import mx.edu.utez.recupera.controller.usuario.dto.UsuarioDto;
import mx.edu.utez.recupera.model.Usuario.Usuario;
import mx.edu.utez.recupera.model.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(generarContrasenaAleatoria());
        usuario.setStatus(true);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            if (usuarioDto.getUsername() != null) {
                usuario.setUsername(usuarioDto.getUsername());
            }

            if (usuarioDto.getPassword() != null) {
                usuario.setPassword(usuarioDto.getPassword());
            }

            if (usuarioDto.getStatus() != null) {
                usuario.setStatus(usuarioDto.getStatus());
            }

            return usuarioRepository.save(usuario);
        }

        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private String generarContrasenaAleatoria() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        int longitud = 8;

        Random random = new Random();
        StringBuilder contrasena = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            contrasena.append(caracteres.charAt(index));
        }

        return contrasena.toString();
    }
}