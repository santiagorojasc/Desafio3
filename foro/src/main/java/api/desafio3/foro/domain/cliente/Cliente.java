package api.desafio3.foro.domain.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Email
    private String email;
    private String telefono;
    @NotBlank
    @Pattern(regexp = "\\d{8,9}")
    private String documento;
    private Boolean activo;


    public Cliente(DatosRegistroCliente datosRegistroUsuario) {
        this.activo = true;
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.documento = datosRegistroUsuario.documento();
        this.telefono = datosRegistroUsuario.telefono();
    }
    public void actualizarDatos(DatosActualizarCliente datosActualizarCliente) {
        if (datosActualizarCliente.nombre() != null) {
            this.nombre = datosActualizarCliente.nombre();
        }
        if (datosActualizarCliente.documento() != null) {
            this.documento = datosActualizarCliente.documento();
        }

    }
    public void desactivarCliente() {
        this.activo = false;
    }



  }
