package api.desafio3.foro.controller;

import api.desafio3.foro.domain.cliente.Cliente;
import api.desafio3.foro.domain.cliente.DatosListadoCliente;
import api.desafio3.foro.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private RegistroDeTopico registro;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosRegistraTopico datos) {

        var detalleConsulta = registro.reservar(datos);

        return ResponseEntity.ok(detalleConsulta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        var datosTopico = new DatosDetalleTopico(
                topico.getId(),
                topico.getCliente().getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion()
        );
        return ResponseEntity.ok(datosTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listadoTopicosTopicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosDetalleTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = repository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
