package com.yat.dao;

import com.yat.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    List<Student> selectStudents();

    Student selectStudentByID(int id);

    List<Student> selectMulitParam(@Param("name")String name, @Param("age")int age);

    boolean insertStudent(Student student);

    List<Student> selectStudentByMap(Map<String,Object> map);

    List<Student> selectAllStudents();

    List<Student> selectUser$Order(@Param("colName") String colName);

    // 第一种模糊查询
    List<Student> selectLikeOne(String name);

    // 第二种模糊查询
    List<Student> selectLikeTwo(String name);

    List<Student> getStuAndDept(Integer id);

    Student getStuByIdStep(Integer id);

    List<Student> getStuByDeptId(Integer id);
}
