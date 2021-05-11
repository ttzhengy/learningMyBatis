package com.yat.dao.impl;

import com.yat.dao.StudentDao;
import com.yat.entity.Student;
import com.yat.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

// 实际上不必要，mybatis有动态代理的实现类

// public class StudentDaoImpl implements StudentDao {
//     @Override
//     public List<Student> selectStudents() {
//         // 获取SqlSession对象
//         SqlSession sqlSession = MyBatisUtils.getSqlSession();
//         String sqlId = "com.yat.dao.StudentDao.selectStudents";
//         List<Student> stuList = sqlSession.selectList(sqlId);
//         // for (Student stu : stuList) {
//         //     System.out.println(stu);
//         // }
//         sqlSession.close();
//         return stuList;
//     }
//
//     @Override
//     public int insertStudent(Student student) {
//         SqlSession sqlSession = MyBatisUtils.getSqlSession();
//         String sqlId = "com.yat.dao.StudentDao.insertStudent";
//         int insert = sqlSession.insert(sqlId, student);
//         sqlSession.commit();
//         sqlSession.close();
//         return insert;
//     }
// }
