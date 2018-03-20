package com.github.sunyueqiao;

import java.lang.reflect.Field;

/**
 * @author sunyueqiao
 * @date 2018-3-20
 * */
public class AutoMapper {
    private Object sourceObj;
    private Object targetObj;

    /**
     * automapper 对象初始化
     */
    public static AutoMapper initilize(Object sourceObj, Object targetObj) {
        AutoMapper autoMapper = new AutoMapper();
        autoMapper.sourceObj = sourceObj;
        autoMapper.targetObj = targetObj;
        return autoMapper;
    }

    /**
     * 获取源对象的属性
     * @return 属性的数组对象
     * */
    private Field[] getSourceObjectFields() {
        if (this.sourceObj == null) {
            System.out.println("源对象不能为null");
            return null;
        }

        return this.sourceObj.getClass().getDeclaredFields();
    }

    /**
     * 获取目标对象的属性
     * @return 属性的数组对象
     * */
    private Field[] getTargetObjectFields() {
        if (this.targetObj == null) {
            System.out.println("目标对象不能为null");
            return null;
        }

        return this.targetObj.getClass().getDeclaredFields();
    }

    /**
     * 映射
     * */
    public Object mapping() {
        System.out.println("mapping");
        Field[] sourceObjFields = this.getSourceObjectFields();
        Field[] targetObjFields = this.getTargetObjectFields();
        for (Field sourceObjField : sourceObjFields) {
            for (Field targetObjField : targetObjFields) {
                if(sourceObjField.getName().equalsIgnoreCase(targetObjField.getName())
                        &&sourceObjField.getType().getName().equalsIgnoreCase(targetObjField.getType().getName())){
                    try {
                        targetObjField.setAccessible(true);
                        sourceObjField.setAccessible(true);
                        //给目标对象赋值
                        targetObjField.set(this.targetObj,sourceObjField.get(this.sourceObj));
                        targetObjField.setAccessible(false);
                        sourceObjField.setAccessible(false);
                    }
                    catch (Exception ex){
                        System.out.println(ex.toString());
                    }
                }
            }
        }

        return this.targetObj;
    }
}
