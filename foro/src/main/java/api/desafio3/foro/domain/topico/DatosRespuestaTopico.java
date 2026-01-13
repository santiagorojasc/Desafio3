package api.desafio3.foro.domain.topico;


import api.desafio3.foro.domain.cliente.Cliente;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje

                                   ) {
}
