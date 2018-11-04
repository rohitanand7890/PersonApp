import dao.PersonCache;
import dao.PersonDAO;
import dao.PostgresPersonDAO;
import exception.NameNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.PersonService;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    PersonCache mockPersonCache =null;
    PersonDAO mockPostgresPersonDAO =null;
    PersonService service=null;
    File file = null;
    @Before
    public void setUp() {
        mockPersonCache =Mockito.mock(PersonCache.class);
        mockPostgresPersonDAO =Mockito.mock(PostgresPersonDAO.class);
        file = Mockito.mock(File.class);
        service=new PersonService(mockPostgresPersonDAO,file, mockPersonCache);
    }
    @Test
    public void getAge_FoundInCache() throws NameNotFoundException {
        when(mockPersonCache.getAgeByName("rohit")).thenReturn(20);
        assertEquals(Optional.of(Integer.valueOf(20)),service.getAgeByName("rohit"));
        Mockito.verify(mockPersonCache).getAgeByName("rohit");
    }
    @Test
    public void getAge_FoundInDBOrNotFoundInCache() throws SQLException, NameNotFoundException {
        when(mockPersonCache.getAgeByName("rohit")).thenThrow(NameNotFoundException.class);
        when(mockPostgresPersonDAO.getAgeByName("rohit")).thenReturn(20);
        assertEquals(Optional.of(Integer.valueOf(20)),service.getAgeByName("rohit"));
    }

    @Test
    public void getAge_NotFoundInCacheAndDB() throws SQLException,NameNotFoundException {
        when(mockPersonCache.getAgeByName("rohit")).thenThrow(NameNotFoundException.class);
        when(mockPostgresPersonDAO.getAgeByName("rohit")).thenThrow(NameNotFoundException.class);
        assertEquals(Optional.empty(),service.getAgeByName("rohit"));
    }
}