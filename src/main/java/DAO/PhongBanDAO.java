package DAO;

import ConnectDB.ConnectDB;
import model.NhanVien;
import model.PhongBan;

import java.sql.*;
import java.util.ArrayList;

public class PhongBanDAO implements IDAO<PhongBan>{

    private static final String SELECT_ALL="select  * from phong";
    @Override
    public ArrayList<PhongBan> selectAll() {
        ArrayList<PhongBan> phongBans =new ArrayList<>();
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("idPhong");
                String name = rs.getString("tenP");

                phongBans.add(new PhongBan(id,name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return phongBans;
    }

    @Override
    public boolean insert(PhongBan phongBan) {
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        return false;
    }


    private static final String SELECT_BY_ID="select * from phong where idPhong= ?" ;
    @Override
    public PhongBan findCByID(int id) {
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            resultSet.next();

            String name=resultSet.getString("tenP");

            return  new PhongBan(id,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean edit(PhongBan phongBan) {
        return false;
    }
}
