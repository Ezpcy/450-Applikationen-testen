package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  AddressService service;

  Address a1, a2;

  @BeforeEach
  void setup() {
    a1 = new Address(1, "Anna", "Müller", "079", new Date(0));
    a2 = new Address(2, "Bernd", "Meier", "078", new Date(0));
  }

  void post_create_returns201AndBody() throws Exception {
    when(service.save(any(Address.class))).thenReturn(a1);

    String json = """
        {"id":1,"firstname":"Anna","lastname":"Müller","phonenumber":"079","registrationDate":"1970-01-01T00:00:00.000+00:00"}
        """;

    mvc.perform(post("/address")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.firstname").value("Anna"))
        .andExpect(jsonPath("$.lastname").value("Müller"));

    verify(service).save(any(Address.class));
  }

  @Test
  void get_all_returns200AndSortedList() throws Exception {
    when(service.getAll()).thenReturn(List.of(a2, a1)); // bereits sortiert vom Service

    mvc.perform(get("/address"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(2))
        .andExpect(jsonPath("$[1].id").value(1));

    verify(service).getAll();
  }

  @Test
  void get_byId_found_returns200() throws Exception {
    when(service.getAddress(1)).thenReturn(Optional.of(a1));

    mvc.perform(get("/address/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1));

    verify(service).getAddress(1);
  }

  @Test
  void get_byId_notFound_returns404() throws Exception {
    when(service.getAddress(99)).thenReturn(Optional.empty());

    mvc.perform(get("/address/99"))
        .andExpect(status().isNotFound());

    verify(service).getAddress(99);
  }
}
