package com.example.demo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class StaffModelMapper implements RowMapper<StaffModel> {
    public StaffModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    	StaffModel StaffModel= new StaffModel();
    	StaffModel.setId(rs.getInt("ID"));
    	StaffModel.setEmail(rs.getString("EMAIL"));
    	StaffModel.setPhone(rs.getString("PHONE"));
    	StaffModel.setPassword(rs.getString("PASSWORD"));
    	StaffModel.setPosition(rs.getString("POSITION"));

        return StaffModel;
    }
}

