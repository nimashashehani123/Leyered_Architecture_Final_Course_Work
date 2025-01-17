package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepo {
    public static boolean saveClient(ClientModel clientModel) throws SQLException {
        String sql = "INSERT INTO Client VALUES(?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,clientModel.getCId());
        pstm.setObject(2, clientModel.getName());
        pstm.setObject(3, clientModel.getAddress());
        pstm.setObject(4, clientModel.getTel());
        pstm.setObject(5, clientModel.getEmail());

        return pstm.executeUpdate() > 0;

    }
    public static boolean deleteClient(String id) throws SQLException {
        String sql = "DELETE FROM Client WHERE CId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static ClientModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Client WHERE CId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            System.out.println("Sout");
            String cl_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);

            ClientModel clientModel = new ClientModel(cl_id, name, address, tel,email);

            return clientModel;
        }

        return null;
    }
    public static boolean updateClient( ClientModel clientModel) throws SQLException {
        String sql = "UPDATE Client SET Name = ?, Address = ?, Tel = ? ,Email = ? WHERE CId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, clientModel.getName());
        pstm.setObject(2, clientModel.getAddress());
        pstm.setObject(3, clientModel.getTel());
        pstm.setObject(4, clientModel.getEmail());
        pstm.setObject(5, clientModel.getCId());

        return pstm.executeUpdate() > 0;
    }
    public static List<ClientModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Client";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ClientModel> clList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String email = resultSet.getString(5);

            ClientModel clientModel = new ClientModel(id, name, address, tel, email);
            clList.add(clientModel);
        }
        return clList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT CId FROM Client";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static boolean isduplicated(String id) throws SQLException {
        String sql = "SELECT * FROM Client WHERE CId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return true;
        }

        return false;
    }


}
