package com.MemberDomain.mapper;

import com.MemberDomain.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface User_mapper {

    final String register = "INSERT INTO tbl_users (id_user, name, email, phoneNumber, password, id_role, is_active)\n" +
            "VALUES (#{name}, #{email}, #{phoneNumber}, #{password}, #{id_role}, #{is_active});";

    final String login = "SELECT * from tbl_user WHERE phoneNumber = #{phoneNumber} AND password = #{password}";
    final String getAll = "SELECT * FROM tbl_users";
    final String getById = "SELECT * FROM tbl_users WHERE id_user = #{id_user}";
    final String getByEmail = "SELECT * FROM tbl_users WHERE email = #{email}";
    final String getByPhoneNumber = "SELECT * FROM tbl_users WHERE phoneNumber = #{phoneNumber}";

    final String update = "UPDATE tbl_users SET name = #{name}, email = #{email}, phoneNumber = #{phoneNumber}, \n" +
            "password = #{password}, id_role = #{id_role}, is_active = #{is_active}, WHERE idUser = #{idUser};";


    final String deleteById = "DELETE from tbl_user WHERE idUser = #{idUser}";

    final String updateToken = "UPDATE tbl_user SET token = #{token} WHERE idUser = #{idUser}";
    final String updateUserBalance = "UPDATE tbl_user SET balance = #{newBalance} WHERE idUser = #{idUser}";

    @Select(getAll)
    List<User> getAll();

    @Select(getById)
    @Results(value = {
            @Result(property = "idUser", column = "idUser"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "token", column = "token")
    })
    User getById(long idUser);

    @Select(getByPhoneNumber)
    @Results(value = {
            @Result(property = "idUser", column = "idUser"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "token", column = "token")
    })
    User getByPhoneNumber(String phoneNumber);

    @Select(login)
    @Results(value = {
            @Result(property = "idUser", column = "idUser"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "token", column = "token")
    })
    User login(User user);

    @Select(emailCheck)
    User emailCheck(User user);

    @Select(phoneCheck)
    User phoneCheck(User user);

    @Update(update)
    void update(User user);

    @Update(updateToken)
    void updateToken(long idUser, int token);

    @Update(updateUserBalance)
    void updateUserBalance(long idUser, float newBalance);

    @Delete(deleteById)
    void delete(long idUser);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "idUser")
    void insert(User user);
}
