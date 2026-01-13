package api.desafio3.foro.domain.cliente;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCliente(@NotNull Long id, String nombre, String documento) {
}
