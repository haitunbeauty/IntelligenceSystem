package com.manage.intelligence.data.db;




import com.manage.intelligence.data.model.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface UserDao {

    @Query("SELECT * FROM  User")
    List<User> getAllUser(); //加载所有动漫数据

    @Query("SELECT * FROM User WHERE userName = :name")
    User loadUserByName(String name); //根据名字加载动漫

    @Insert
    void insertOneUser(User User); //插入一条动漫信息

    @Insert
    void insertMultiUsers(User... Users); //插入多条动漫信息

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUsers(User... Users); //更新动漫信息，当有冲突时则进行替代

    //删全部
    @Query("DELETE FROM User")
    void deleteAll();

    @Delete
    void deleteUser(User User);

}
