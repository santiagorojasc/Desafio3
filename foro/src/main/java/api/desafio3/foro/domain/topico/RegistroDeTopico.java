package api.desafio3.foro.domain.topico;

import api.desafio3.foro.domain.ValidacionException;
import api.desafio3.foro.domain.cliente.Cliente;
import api.desafio3.foro.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroDeTopico {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TopicoRepository topicoRepository;


    public DatosDetalleTopico reservar(DatosRegistraTopico datos){

        if(!clienteRepository.existsById(datos.idCliente())){
            throw new ValidacionException("No existe un paciente con el id informado");
        }

        if(datos.idCliente() != null && !clienteRepository.existsById(datos.idCliente())){
            throw new ValidacionException("No existe un cliente con el id informado");
        }
        var topicoActivo = true;
        var fechaactual= LocalDateTime.now();
        var cliente = clienteRepository.findById(datos.idCliente()).get();
        var topico = new Topico(
                null,
                cliente,
                datos.titulo(),
                datos.mensaje(),
                fechaactual,
                topicoActivo);
       topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }


}
