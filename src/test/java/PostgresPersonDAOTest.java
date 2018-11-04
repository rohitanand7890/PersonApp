import dao.PostgresPersonDAO;
import org.junit.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;

public class PostgresPersonDAOTest {

    @Test
    public void getAgeByNameFail() throws SQLException {
        PostgresPersonDAO dbMock= mock(PostgresPersonDAO.class);
        //when(dbMock.getAgeByName("rohit")).thenThrow(new RuntimeException("something"));
       // assertEquals( "20",dbMock.getAgeByName("rohit"));
    }

    @Test
    public void upsertHit() throws SQLException {
        PostgresPersonDAO dbMock= mock(PostgresPersonDAO.class);
        dbMock.upsertPerson("rohit",20);
       //assertEquals( "20",dbMock.getAgeByName("rohit"));
    }

    @Test
    public void deleteAll() {
    }
}