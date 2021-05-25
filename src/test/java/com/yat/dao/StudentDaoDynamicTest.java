package com.yat.dao;

import com.yat.entity.Department;
import com.yat.entity.Student;
import com.yat.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoDynamicTest {

    @Test
    public void testGetStuByConditionIf(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDaoDynamic dao = sqlSession.getMapper(StudentDaoDynamic.class);
        Student stu = new Student();
        stu.setId(1001);
        // stu.setName("%张%");
        List<Student> studentList = dao.getStuByConditionIf(stu);
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetStuByConditionTrim(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDaoDynamic dao = sqlSession.getMapper(StudentDaoDynamic.class);
        Student stu = new Student();
        stu.setId(1001);
        // stu.setName("%张%");
        List<Student> studentList = dao.getStuByConditionTrim(stu);
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetStuByConditionChoose(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDaoDynamic dao = sqlSession.getMapper(StudentDaoDynamic.class);
        Student stu = new Student();
        // stu.setId(1001);
        // stu.setName("%张%");
        List<Student> studentList = dao.getStuByConditionChoose(stu);
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    @Test
    public void testUpdateStu(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDaoDynamic dao = sqlSession.getMapper(StudentDaoDynamic.class);
        Student stu = new Student();
        stu.setId(1006);
        // stu.setName("马超");
        stu.setEmail("zhaoyun@qq.com");
        // stu.setAge(25);
        dao.updateStu(stu);
        sqlSession.commit();
    }

    @Test
    public void testGetStuByConditionForeach(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDaoDynamic dao = sqlSession.getMapper(StudentDaoDynamic.class);
        List<Student> studentList = dao.getStuByConditionForeach(Arrays.asList(1001,1003,1005));
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    @Test
    public void testAddStus(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDaoDynamic dao = sqlSession.getMapper(StudentDaoDynamic.class);
        List<Student> stus = new ArrayList<>();
        stus.add(new Student(null,"黄忠","huangzhong@sina.vip.com",40,new Department(1)));
        stus.add(new Student(null,"庞统","pangtong@321.com",25,new Department(3)));
        dao.addStus(stus);
        sqlSession.commit();
    }
}