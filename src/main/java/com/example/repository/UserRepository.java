package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Mapper
@Repository
public interface UserRepository {

    @Insert("insert into user (id, name, age) values (#{id}, #{name}, #{age})")
    int create(User user);

    @Update("update user set name = #{name}, age = #{age} where id = #{id}")
    int update(User user);

    @Select("select id, name, age from user where id = #{id}")
    User findById(int id);

    @Select("select id, name, age from user order by id desc")
    List<User> findAll();

    @Select("select id, name, age from user where id = ${_parameter} order by id desc")
    List<User> search(String condition);
}
