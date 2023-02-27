package Taxi_Service.model.DAO;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.dao.UserDAO;
import com.epam.taxi_service.models.dao.for_the_database.DAOUserSQL;
import com.epam.taxi_service.models.entities.User;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static Taxi_Service.Constants.*;
import static Taxi_Service.model.DAO.DAOTestUtils.getTestUser;
import static com.epam.taxi_service.models.dao.for_the_database.queries_and_constants.SQLFields.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class userDAOTest {
    @Test
    void testAdd() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement ignored = prepareMocks(dataSource)) {
            assertDoesNotThrow(() -> userDAO.add(getTestUser()));
        }
    }
    @Test
    void testSqlExceptionAdd() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.add(getTestUser()));
    }
    @Test
    void testGetByEmail() throws DAOException, SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement preparedStatement = prepareMocks(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            prepareResultSet(resultSet);
            User resultUser = userDAO.getByEmail(EMAIL_VALUE).orElse(null);
            assertNotNull(resultUser);
            assertEquals(getTestUser(), resultUser);
        }
    }

    @Test
    void testGetByEmailAbsent() throws SQLException, DAOException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement preparedStatement = prepareMocks(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);
            User resultUser = userDAO.getByEmail(EMAIL_VALUE).orElse(null);
            assertNull(resultUser);
        }
    }

    @Test
    void testSqlExceptionGetByEmail() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.getByEmail(EMAIL_VALUE));
    }

    @Test
    void testGetAll() throws DAOException, SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement preparedStatement = prepareMocks(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            prepareResultSet(resultSet);
            List<User> users = userDAO.getAll();
            assertEquals(ONE, users.size());
            assertEquals(getTestUser(), users.get(0));
        }
    }

    @Test
    void testSqlExceptionGetAll() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, userDAO::getAll);
    }
    @Test
    void testGetAllSorted() throws DAOException, SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement preparedStatement = prepareMocks(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            prepareResultSet(resultSet);
            List<User> users = userDAO.getSorted("query");
            assertEquals(ONE, users.size());
            assertEquals(getTestUser(), users.get(0));
        }
    }
    @Test
    void testSqlExceptionGetAllSorted() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.getSorted("query"));
    }

    @Test
    void testGetNumberOfRecords() throws DAOException, SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement preparedStatement = prepareMocks(dataSource)) {
            ResultSet resultSet = mock(ResultSet.class);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true);
            when(resultSet.getInt(NUMBER_OF_RECORDS)).thenReturn(100);
            int records = userDAO.getNumberOfRecords("filter");
            assertEquals(100, records);
        }
    }

    @Test
    void testSqlExceptionGetNumberOfRecords() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.getNumberOfRecords("filter"));
    }

    @Test
    void testUpdate() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement ignored = prepareMocks(dataSource)) {
            assertDoesNotThrow(() -> userDAO.update(getTestUser()));
        }
    }

    @Test
    void testSqlExceptionUpdate() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.update(getTestUser()));
    }

    @Test
    void testUpdatePassword() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement ignored = prepareMocks(dataSource)) {
            assertDoesNotThrow(() -> userDAO.updatePassword(getTestUser()));
        }
    }

    @Test
    void testSqlExceptionUpdatePassword() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.updatePassword(getTestUser()));
    }


    @Test
    void testDelete() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        try (PreparedStatement ignored = prepareMocks(dataSource)) {
            assertDoesNotThrow(() -> userDAO.delete(ID_VALUE));
        }
    }

    @Test
    void testSqlExceptionDelete() throws SQLException {
        DataSource dataSource = mock(DataSource.class);
        UserDAO userDAO = new DAOUserSQL(dataSource);
        when(dataSource.getConnection()).thenThrow(new SQLException());
        assertThrows(DAOException.class, () -> userDAO.delete(ID_VALUE));
    }

    private PreparedStatement prepareMocks(DataSource dataSource) throws SQLException {
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(isA(String.class))).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(isA(int.class), isA(int.class));
        doNothing().when(preparedStatement).setLong(isA(int.class), isA(long.class));
        doNothing().when(preparedStatement).setString(isA(int.class), isA(String.class));
        when(preparedStatement.execute()).thenReturn(true);
        return preparedStatement;
    }
    private static void prepareResultSet(ResultSet resultSet) throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(ID)).thenReturn(ID_VALUE);
        when(resultSet.getString(EMAIL)).thenReturn(EMAIL_VALUE);
        when(resultSet.getString(NAME)).thenReturn(NAME_VALUE);
        when(resultSet.getString(SURNAME)).thenReturn(SURNAME_VALUE);
        when(resultSet.getString(PASSWORD)).thenReturn(PASSWORD_VALUE);
        when(resultSet.getInt(ROLE_ID)).thenReturn(ROLE_ID_VALUE);
    }
}
