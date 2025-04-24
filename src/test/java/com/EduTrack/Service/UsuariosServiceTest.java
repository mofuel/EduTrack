package com.EduTrack.Service;

import com.EduTrack.Model.Usuarios;
import com.EduTrack.Repository.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuariosServiceTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private UsuariosService usuariosService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        Usuarios u1 = new Usuarios();
        u1.setId("U0001");
        u1.setNombre("Juan");

        Usuarios u2 = new Usuarios();
        u2.setId("U0002");
        u2.setNombre("Maria");

        when(usuariosRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuarios> lista = usuariosService.listarTodos();
        assertEquals(2, lista.size());
        assertEquals("Juan", lista.get(0).getNombre());
    }

    @Test
    public void testGuardarUsuario() {
        Usuarios u = new Usuarios();
        u.setId("U0003");
        u.setNombre("Pedro");

        when(usuariosRepository.save(u)).thenReturn(u);

        Usuarios resultado = usuariosService.guardar(u);
        assertNotNull(resultado);
        assertEquals("Pedro", resultado.getNombre());
        verify(usuariosRepository, times(1)).save(u); // Verificación adicional para asegurar que se guarda
    }

    @Test
    public void testBuscarPorId() {
        Usuarios u = new Usuarios();
        u.setId("U0004");
        u.setNombre("Lucia");

        when(usuariosRepository.findById("U0004")).thenReturn(Optional.of(u));

        Optional<Usuarios> resultado = usuariosService.buscarPorId("U0004"); // Cambiado a String
        assertTrue(resultado.isPresent());
        assertEquals("Lucia", resultado.get().getNombre());
    }

    @Test
    public void testEliminar() {
        String id = "U0001";  // Cambiar el ID a String
        doNothing().when(usuariosRepository).deleteById(id);
        usuariosService.eliminar(id);
        verify(usuariosRepository, times(1)).deleteById(id);
    }

}