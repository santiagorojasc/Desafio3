package api.desafio3.foro.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistraTopico(
        Long idCliente,
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean activo

) {
}
