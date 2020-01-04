package infra;

 
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionPool {
	
	private DataSource dataSource;
	private String dbName = "fretedb";
	
	public ConnectionPool(){
		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		String url = "jdbc:mysql://localhost?useSSL=false&Unicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		mysqlDataSource.setUrl(url);
		mysqlDataSource.setDatabaseName(dbName);
		mysqlDataSource.setUser("root");
		mysqlDataSource.setPassword("");
		
		this.dataSource = mysqlDataSource;
	}
	
	public Connection getConnection() throws SQLException{
		System.out.println("Tentando conectar ...");
		Connection conexao = (Connection) dataSource.getConnection();
		System.out.println("Conectado com sucesso ...");
		return conexao;
	}

}
