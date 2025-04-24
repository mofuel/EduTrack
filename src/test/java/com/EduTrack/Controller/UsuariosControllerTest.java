package com.EduTrack.Controller;

import com.EduTrack.Model.Usuarios;
import com.EduTrack.Service.UsuariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class UsuariosControllerTest {

    @Mock
    private UsuariosService usuariosService; // Mock del servicio

    @InjectMocks
    private UsuariosController usuariosController; // Inyecta el mock en el controlador

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Inicializa MockMvc antes de cada test
        mockMvc = MockMvcBuilders.standaloneSetup(usuariosController).build();
    }

    @Test
    public void testObtenerTodos() throws Exception {
        // Crea el usuario mockeado
        Usuarios u = new Usuarios();
        u.setId("U0010");
        u.setNombre("Laura");

        // Simula el comportamiento del servicio
        when(usuariosService.listarTodos()).thenReturn(List.of(u));

        // Realiza la prueba
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Laura"));
    }

    @Test
    public void testCrearUsuario() throws Exception {
        // Crea el usuario mockeado
        Usuarios u = new Usuarios();
        u.setId("U0011");
        u.setNombre("Dani");

        // Simula el comportamiento del servicio
        when(usuariosService.guardar(any(Usuarios.class))).thenReturn(u);

        // Realiza la prueba de creación
        mockMvc.perform(post("/api/usuarios")
                        .contentType("application/json")
                        .content("{\"id\":\"U0011\",\"nombre\":\"Dani\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Dani"));
    }
}
