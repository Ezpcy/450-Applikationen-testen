package ch.tbz.m450.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import ch.tbz.m450.service.AddressService;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressServiceTests {
  @Mock
  AddressRepository repo;

  @InjectMocks
  AddressService service;

  Address z1, z2, b1;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    z1 = new Address(1, "Anna", "Zürcher", "079", new Date());
    z2 = new Address(2, "Bernd", "Zürcher", "078", new Date());
    b1 = new Address(3, "Claudia", "Aarau", "0077", new Date());
  }

  @Test
  void save_delegatesToRepository() {
    when(repo.save(z1)).thenReturn(z1);

    Address saved = service.save(z1);

    assertEquals(z1, saved);
    verify(repo).save(z1);
  }

  @Test
  void getAddress_found_returnsOptionalWithValue() {
    when(repo.findById(1)).thenReturn(Optional.of(z1));

    Optional<Address> res = service.getAddress(1);

    assertTrue(res.isPresent());
    assertEquals(1, res.get().getId());
    verify(repo).findById(1);
  }

  @Test
  void getAddress_notFound_returnsEmpty() {
    when(repo.findById(99)).thenReturn(Optional.empty());

    Optional<Address> res = service.getAddress(99);

    assertTrue(res.isEmpty());
    verify(repo).findById(99);
  }

  @Test
  void getAll_returnsSortedList_viaComparator() {
    when(repo.findAll()).thenReturn(new ArrayList<>(List.of(z2, z1, b1)));

    List<Address> sorted = service.getAll();

    assertEquals(List.of(b1, z1, z2), sorted);
    verify(repo).findAll();
  }
}
