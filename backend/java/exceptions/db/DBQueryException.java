package exceptions.db;

public class DBQueryException extends RuntimeException {

    public DBQueryException()
    {
        super("DB query error.");
    }
    public DBQueryException(String errorReason)
    {
        super("DB query error. "+errorReason);
    }
    public DBQueryException(String location,String errorReason)
    {
        super("DB query error. At "+location+". Due to "+errorReason);
    }
}
