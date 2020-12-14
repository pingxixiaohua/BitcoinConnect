package com.btc.rpc.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONDemo {
    public static void main(String[] args) {
        System.out.println("hello world");
        //fastjson的使用方法
        //

        //1、类对象进行json的序列化和反序列化
        //内存中student对象
        Student student = new Student();
        student.id = "10001";
        student.name = "老王";
        student.sex = "male";
        student.age = 18;

        //序列化后的对象字符串
        String stuJson = JSONObject.toJSON(student).toString();
        System.out.println(stuJson);

        //反序列化变成内存对象
        Student stu1 = JSONObject.parseObject(stuJson,Student.class);
        System.out.println(stu1.getName());

        /**
         * 将类对象转换为JSONObject类型
         * JSONObject object = (JSONObject)JSONObject.toJSON(对象名);
         */

        //2、利用JsonObject直接生成
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","王大锤");
        jsonObject.put("sex","女");
        jsonObject.put("age","19");
        jsonObject.put("id","10001");
        String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString);

//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(0,student);
//        String arrayString = jsonArray.toString();


    }

    static class Student{
        private String id;
        private String name;
        private String sex;
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
