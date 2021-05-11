package com.yat;

import com.yat.dao.StudentDao;
import com.yat.entity.Student;
import com.yat.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {

    @Test
    public void testSelectStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectStudents();
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testInsertStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setId(1006);
        student.setName("赵云");
        student.setEmail("赵云@qq.com");
        student.setAge(24);
        int i = dao.insertStudent(student);
        sqlSession.commit();
        System.out.println(i);
    }

    @Test
    public void testSelectStudentByID(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectStudentByID(1003);
        System.out.println(student);
    }

    @Test
    public void testSelectMulitParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectMulitParam("刘备",20);
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectByMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Map<String,Object> map = new HashMap<>();
        map.put("name","关羽");
        map.put("age",24);
        List<Student> studentList = dao.selectStudentByMap(map);
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testSelect$Order(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectUser$Order("email");
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectAllStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = dao.selectAllStudents();
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectLikeOne(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        String name = "%李%";
        List<Student> studentList = dao.selectLikeOne(name);
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectLikeTwo(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        String name = "李";
        List<Student> studentList = dao.selectLikeTwo(name);
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }
}
