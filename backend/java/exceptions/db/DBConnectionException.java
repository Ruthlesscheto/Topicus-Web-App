package exceptions.db;

import java.sql.SQLException;

public class DBConnectionException extends SQLException {
    public DBConnectionException(String errorReason) {
        super("DB Driver could not connect to DB Server because: "+errorReason);
    }
}
