import dao.PersonCache;
import exception.NameNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PersonCacheTest {

    @Test
    public void put() {
    }

    @Test(expected = RuntimeException.class)
    public void getAgeOfNameCacheFail() throws NameNotFoundException {
        PersonCache personCacheMock = mock(PersonCache.class);
        when(personCacheMock.getAgeByName("rohit")).thenThrow(new RuntimeException("something"));
        personCacheMock.getAgeByName("rohit");
    }
    @Test(expected = RuntimeException.class)
    public void getAgeOfNameCacheHit() throws NameNotFoundException {
        PersonCache personCacheMock = mock(PersonCache.class);

        personCacheMock.putPerson("rohit",20);
        assertEquals(java.util.Optional.of(20), personCacheMock.getAgeByName("rohit"));
    }
}