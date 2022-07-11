package DAO;

import ConnectDB.ConnectDB;
import model.NhanVien;
import model.PhongBan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NVDAO implements IDAO<NhanVien>{

    PhongBanDAO phongBanDAO =new PhongBanDAO();


    private static  final  String SELECT_ALL="select * from nhanvien";



    @Override
    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> nhanViens =new ArrayList<>();
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("idNV");
                String name = rs.getString("ten");
                Date birthDay = rs.getDate("ngaySinh");
                String address = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                int map=rs.getInt("idP");
                nhanViens.add(new NhanVien(id,name,birthDay,address,sdt,email,phongBanDAO.findCByID(map)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return nhanViens;
    }

    private static  final  String INSERT_NV_SQL="insert into nhanvien(ten,ngaySinh,diachi,sdt,email,idP) values(?,?,?,?,?,?)";

    @Override
    public boolean insert(NhanVien nhanVien) {
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NV_SQL)){
            preparedStatement.setString(1,nhanVien.getTen());
            preparedStatement.setDate(2, Date.valueOf(String.valueOf(nhanVien.getNgaySinh())));
            preparedStatement.setString(3,nhanVien.getDiaChi());
            preparedStatement.setString(4,nhanVien.getSdt());
            preparedStatement.setString(5,nhanVien.getEmail());
            preparedStatement.setInt(6,nhanVien.getPhongBan().getIdP());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {

        }
        return false;
    }
    private static  final  String DELETE="delete  from nhanvien where idNV=?";
    @Override
    public boolean deleteByID(int id) {
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }


    private static final String SELECT_BY_ID="select * from nhanvien where idNV= ?" ;
    @Override
    public NhanVien findCByID(int id) {
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet rs= preparedStatement.executeQuery();
            rs.next();

                String name = rs.getString("ten");
                Date birthDay = rs.getDate("ngaySinh");
                String address = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                int map=rs.getInt("idP");
                return new  NhanVien(id,name,birthDay,address,sdt,email,phongBanDAO.findCByID(map));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean edit(NhanVien nhanVien) {
        return false;
    }

    private static  final  String UPDATE_NV_SQL="update nhanvien set ten=?,ngaySinh=?,diachi=?,sdt=?,email=?,idP=? where idNV=?";

    public boolean edit(NhanVien nhanVien ,int id) {
        try (Connection connection= ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NV_SQL)){
            preparedStatement.setString(1,nhanVien.getTen());
            preparedStatement.setDate(2, Date.valueOf(String.valueOf(nhanVien.getNgaySinh())));
            preparedStatement.setString(3,nhanVien.getDiaChi());
            preparedStatement.setString(4,nhanVien.getSdt());
            preparedStatement.setString(5,nhanVien.getEmail());
            preparedStatement.setInt(6,nhanVien.getPhongBan().getIdP());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {

        }
        return false;

    }
    private static final String SEARCH_HS = "select * from nhanvien where ten like ? ;";

    public ArrayList<NhanVien> search(String key) {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_HS)) {
            preparedStatement.setString(1,key);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idNV");
                String name = rs.getString("ten");
                Date birthDay = rs.getDate("ngaySinh");
                String address = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                int map = rs.getInt("idP");
                nhanViens.add(new NhanVien(id, name, birthDay, address, sdt, email, phongBanDAO.findCByID(map)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return nhanViens;
    }
}
