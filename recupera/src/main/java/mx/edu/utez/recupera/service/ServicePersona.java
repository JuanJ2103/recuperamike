package mx.edu.utez.recupera.service;

import mx.edu.utez.recupera.controller.persona.dto.PersonaDto;
import mx.edu.utez.recupera.model.Persona.Persona;
import mx.edu.utez.recupera.model.Persona.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicePersona {
    @Autowired
    private PersonaRepository personaRepository;

    public Persona crearPersona(PersonaDto personaDto) {
        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setApellidos(personaDto.getApellidos());
        persona.setDireccion(personaDto.getDireccion());
        persona.setContacto(personaDto.getContacto());

        return personaRepository.save(persona);
    }

    public List<Persona> obtenerTodasPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id);
    }

    public Persona actualizarPersona(Long id, PersonaDto personaDto) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);

        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();

            persona.setNombre(personaDto.getNombre());
            persona.setApellidos(personaDto.getApellidos());
            persona.setDireccion(personaDto.getDireccion());
            persona.setContacto(personaDto.getContacto());

            return personaRepository.save(persona);
        }

        return null;
    }

    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
}


