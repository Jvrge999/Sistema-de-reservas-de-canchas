package cl.duoc.msequipamiento.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EquipamientoEntityTest {

    @Test
    @DisplayName("Debe asignar y retornar valores mediante getters y setters")
    void debeAsignarYRetornarValores() {
        // Arrange (Given)
        EquipamientoEntity entity = new EquipamientoEntity();
        
        // Act (When)
        entity.setId(1L);
        entity.setNombre("Balon de Futbol");
        entity.setTipo("Deportivo");
        entity.setPrecioArriendo(1500.0);
        entity.setDisponible(true);
        
        // Assert (Then)
        assertEquals(1L, entity.getId());
        assertEquals("Balon de Futbol", entity.getNombre());
        assertEquals("Deportivo", entity.getTipo());
        assertEquals(1500.0, entity.getPrecioArriendo());
        assertTrue(entity.getDisponible());
    }
}