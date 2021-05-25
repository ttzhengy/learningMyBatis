package com.yat.dao;

import com.yat.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDaoDynamic {

    // 在待查询属性上带有待查询字段的值
    public List<Student> getStuByConditionIf(Student stu);

    public List<Student> getStuByConditionTrim(Student stu);

    public List<Student> getStuByConditionChoose(Student stu);

    public void updateStu(Student stu);

    public List<Student> getStuByConditionForeach(List<Integer> id);

    public void addStus(@Param("stus") List<Student> stus);
}
