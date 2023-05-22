package db;

/**
 * @author ferruje
 **/
public class DbException extends RuntimeException{
    private static final long servialVersionUID = 1L;
    
    public DbException(String msg){
        super(msg);
    }
}
