package com.aurora.dao.impl;

import com.aurora.dao.BookDao;

import java.lang.reflect.Array;
import java.util.*;

public class BookDaoImpl implements BookDao {

    private int[] array;

    private List<String> list;

    private Set<String> set;

    private Map<String,String> map;

    private Properties properties;

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void save(){
        System.out.println("book dao save...");

        System.out.println("遍历数组：" + Arrays.toString(array));

        System.out.println("遍历list" +list);

        System.out.println("遍历map" +map);

        System.out.println("遍历Properties" +properties);

    }


}