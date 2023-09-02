package br.rafael.restapi2.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.rafael.restapi2.model.User;

//CRUD JAVA MYSQL

public class MySQLBanco {
	private String url = "jdbc:mysql://localhost/mydatabase";
	private String user = "root";
    private String password = "";
    private Connection connection;
    private PreparedStatement preparedStatement;
    
	public MySQLBanco(String url, String user, String password) {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MySQLBanco() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//DANGEROUS
//	public void runSql(String sqlQuery) {
//		try {
//			preparedStatement = connection.prepareStatement(sqlQuery);
//			ResultSet resultSet = preparedStatement.executeQuery();
//	        while (resultSet.next()) {
//	            int id = resultSet.getInt("id");
//	            String name = resultSet.getString("name");
//	            String email = resultSet.getString("email");
//	            System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
//	        }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void insertData(String name, String email) {
        String insertQuery = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Inserted " + rowsInserted + " row(s)");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public String readData() {
		String sqlQuery = "select * from users";
		String result = "";
		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            String email = resultSet.getString("email");
	            result += "ID: " + id + ", Name: " + name + ", Email: " + email + "\n";
	        }
            System.out.println(result);
            return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateData(String newName, String email) {
		
		String updateQuery = "UPDATE users SET name = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, email);
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("Updated " + rowsUpdated + " row(s)");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void deleteData(String email){
        String deleteQuery = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
        	System.out.println(email);
            preparedStatement.setString(1, email);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " row(s)");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	

	public User findUserById(Long id) {
		String sqlQuery = "select * from users where id = ?";
		String result = "";
		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            Long id2 = resultSet.getLong("id");
	            String name = resultSet.getString("name");
	            String email = resultSet.getString("email");
	            return new User(resultSet.getString("name"), resultSet.getString("email"));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}

