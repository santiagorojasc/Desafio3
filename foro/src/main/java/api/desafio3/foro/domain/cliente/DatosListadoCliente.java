package api.desafio3.foro.domain.cliente;

public record DatosListadoCliente(
        Long id,
        String nombre,
        String documento,
        String email) {

    public DatosListadoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNombre(), cliente.getDocumento(), cliente.getEmail());
    }
}


