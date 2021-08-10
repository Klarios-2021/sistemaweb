package com.klarios.sistemaweb;

import com.klarios.sistemaweb.models.Dimension;
import com.klarios.sistemaweb.models.Sala;
import com.klarios.sistemaweb.models.ensayos.EnsayoVariablesAmbientales;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EnsayosTests {

    @Test
    void unaSalaCon4m2Requiere2Mediciones() throws Exception {
        Sala sala = new Sala();
        sala.setDimension(new Dimension(2.0,2.0,2.0));
        EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
        ensayoVariablesAmbientales.setMaterial(sala);
        assertThat(ensayoVariablesAmbientales.obtenerMedicionesNecesarias()).isEqualTo(2);
    }
    @Test
    void unaSalaCon8m2Requiere4Mediciones() throws Exception {
        Sala sala = new Sala();
        sala.setDimension(new Dimension(2.0,2.0,4.0));
        EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
        ensayoVariablesAmbientales.setMaterial(sala);
        assertThat(ensayoVariablesAmbientales.obtenerMedicionesNecesarias()).isEqualTo(4);
    }
    @Test
    void unaSalaCon14m2Requiere7Mediciones() throws Exception {
        Sala sala = new Sala();
        sala.setDimension(new Dimension(2.0,2.0,7.0));
        EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
        ensayoVariablesAmbientales.setMaterial(sala);
        assertThat(ensayoVariablesAmbientales.obtenerMedicionesNecesarias()).isEqualTo(7);
    }
    @Test
    void unaSalaCon7m2Requiere3Mediciones() throws Exception {
        Sala sala = new Sala();
        sala.setDimension(new Dimension(2.0,2.0,3.5));
        EnsayoVariablesAmbientales ensayoVariablesAmbientales = new EnsayoVariablesAmbientales();
        ensayoVariablesAmbientales.setMaterial(sala);
        assertThat(ensayoVariablesAmbientales.obtenerMedicionesNecesarias()).isEqualTo(3);
    }

}
