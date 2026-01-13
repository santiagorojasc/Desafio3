package api.desafio3.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        Long idCliente,
        String titulo,
        String mensaje,
        LocalDateTime fecha) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getCliente().getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion()

        );
    }
}
