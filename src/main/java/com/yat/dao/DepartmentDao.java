package com.yat.dao;

import com.yat.entity.Department;

public interface DepartmentDao {

    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
