package util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * ��ʼ�����ݿ����ӳ�
 */
public class DbcpConnectionPool {
	private static BasicDataSource dataSource = null;
	private static Connection conn = null;
	
	public DbcpConnectionPool(){
	}
	
	/**
	 * 初始化数据库连接�?
	 */
	public static void init(){
		if (dataSource != null){
			try {
				dataSource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataSource = null;
		}
		//使用Properties对象定义数据库连接池信息
		try {
			Properties p = new Properties();
			p.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
			p.setProperty("url", "jdbc:mysql://localhost:3306/wsdc?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
			p.setProperty("username", "root");
			p.setProperty("password", "root");
			p.setProperty("maxActive", "30");				//设置处于活动状�?�的数据库连接的�?大数目，0表示不受限制
			p.setProperty("maxIdle", "10");					//设置处于空闲状�?�的数据库连接的�?大数目，0表示不受限制
			p.setProperty("maxWait", "1000");				//设置没有处于空闲状�?�的连接时，请求数据库连接所�?的最长等待时间（单位ms），如果超出改时间将抛出异常�?-1表示无限期等�?
			p.setProperty("removeAbandoned", "false");
			p.setProperty("removeAbandonedTimeout", "120");
			p.setProperty("testOnBorrow", "true");
			p.setProperty("logAbandoned", "true");
			//以指定信息创建数据源
			dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连�?
	 * @return Connection对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/wsdc?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
		String user = "root";
		String psw = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,psw);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		if (dataSource == null){
//			init();
//		}
//		if (dataSource != null){
//			conn = dataSource.getConnection();
//		}
		return conn;
	}
	/**
	 * 执行select语句
	 * @param sql 要执行的sql语句
	 * @return 结果�?
	 */
	public static ResultSet runSelect(String sql){
		ResultSet rs = null;
		try {
			Statement stat = conn.createStatement();
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 执行增删改sql语句
	 * @param sql 要执行的sql语句
	 * @return 改变的记录数
	 */
	public static int runSql(String sql){
		int i = 0;
		try {
			Statement stat = conn.createStatement();
			i = stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
