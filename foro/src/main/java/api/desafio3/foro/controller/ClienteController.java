package api.desafio3.foro.controller;


import api.desafio3.foro.domain.cliente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCliente> registrarUsuario(@RequestBody @Valid DatosRegistroCliente datos,
                                                                  UriComponentsBuilder uriComponentsBuilder) {


        Cliente cliente = repository.save(new Cliente(datos));
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDocumento());

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCliente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCliente>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosListadoCliente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarCliente datosActualizarUsuario) {
        Cliente cliente = repository.getReferenceById(datosActualizarUsuario.id());
        cliente.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getEmail(),
                cliente.getTelefono(), cliente.getDocumento()
                ));
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        Cliente cliente = repository.getReferenceById(id);
        cliente.desactivarCliente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCliente> retornaDatosUsuario(@PathVariable Long id) {
        Cliente cliente = repository.getReferenceById(id);
        var datos = new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getEmail(),
                cliente.getTelefono(), cliente.getDocumento());
        return ResponseEntity.ok(datos);
    }

}
