package ec.capitales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.capitales.dto.ProvinciaRequest;
import ec.capitales.dto.ProvinciaResponse;

@Service
public class ProvinciaService {

    public List<ProvinciaResponse> traerProvincia(){
        List<ProvinciaResponse> lstProvincia = new ArrayList<ProvinciaResponse>();

        ProvinciaResponse provincia = new ProvinciaResponse();
        provincia.setId(1);
        provincia.setProvincia("Guayas");
        provincia.setCapital("Guayaquil");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(2);
        provincia.setProvincia("Pichincha");
        provincia.setCapital("Quito");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(3);
        provincia.setProvincia("El Oro");
        provincia.setCapital("Machala");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(4);
        provincia.setProvincia("Imbabura");
        provincia.setCapital("Ibarra");
        lstProvincia.add(provincia);

        return lstProvincia;
    }

    public List<ProvinciaResponse> ingresarProvincia(ProvinciaRequest nuevaProvincia) {
        List<ProvinciaResponse> lstProvincia = new ArrayList<ProvinciaResponse>();

        ProvinciaResponse provincia = new ProvinciaResponse();
        provincia.setId(1);
        provincia.setProvincia("Guayas");
        provincia.setCapital("Guayaquil");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(2);
        provincia.setProvincia("Pichincha");
        provincia.setCapital("Quito");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(3);
        provincia.setProvincia("El Oro");
        provincia.setCapital("Machala");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(4);
        provincia.setProvincia("Imbabura");
        provincia.setCapital("Ibarra");
        lstProvincia.add(provincia);

        provincia = new ProvinciaResponse();
        provincia.setId(nuevaProvincia.getId());
        provincia.setProvincia(nuevaProvincia.getProvincia());
        provincia.setCapital(nuevaProvincia.getCapital());
        lstProvincia.add(provincia);

        return lstProvincia;
    }
}
