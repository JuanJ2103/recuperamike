package mx.edu.utez.recupera.controller.persona;

import mx.edu.utez.recupera.controller.persona.dto.PersonaDto;
import mx.edu.utez.recupera.model.Persona.Persona;
import mx.edu.utez.recupera.service.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/personas")
@CrossOrigin(origins = {"*"})
public class PersonaController {

    @Autowired
    private ServicePersona servicePersona;

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody PersonaDto personaDto) {
        Persona nuevaPersona = servicePersona.crearPersona(personaDto);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodasPersonas() {
        List<Persona> personas = servicePersona.obtenerTodasPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        Optional<Persona> persona = servicePersona.obtenerPersonaPorId(id);
        return persona.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody PersonaDto personaDto) {
        Persona personaActualizada = servicePersona.actualizarPersona(id, personaDto);
        if (personaActualizada != null) {
            return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        servicePersona.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

