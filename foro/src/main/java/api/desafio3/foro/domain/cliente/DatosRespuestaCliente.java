package api.desafio3.foro.domain.cliente;

public record DatosRespuestaCliente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento
                                    ) {
}
