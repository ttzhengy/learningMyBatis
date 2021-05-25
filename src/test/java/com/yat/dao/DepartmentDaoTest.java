package com.yat.dao;

import com.yat.entity.Department;
import com.yat.entity.Student;
import com.yat.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDaoTest {

    @Test
    public void testGetStuAndDept(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        DepartmentDao dao = sqlSession.getMapper(DepartmentDao.class);
        Department dept = dao.getDeptById(2);
        System.out.println(dept);
    }

    @Test
    public void testGetDeptByIdPlus(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        DepartmentDao dao = sqlSession.getMapper(DepartmentDao.class);
        Department dept = dao.getDeptByIdPlus(2);
        System.out.println(dept);
    }

    @Test
    public void testGetDeptByIdStep(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        DepartmentDao dao = sqlSession.getMapper(DepartmentDao.class);
        Department dept = dao.getDeptByIdStep(3);
        System.out.println(dept.getDepartmentName());
        System.out.println(dept);
    }
}