package com.tom.redisdiscover.domain.entity;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName Car.java
 * @Description TODO
 * @createTime 2024年12月26日 07:27:00
 */
public class Car {

    /**
     * 编号
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 产地
     */
    private String birthAddr;

    public static Car build(int id, String name, String birthAddr) {
        Car car = new Car();
        car.id = id;
        car.name = name;
        car.birthAddr = birthAddr;
        return car;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthAddr='" + birthAddr + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthAddr() {
        return birthAddr;
    }

    public void setBirthAddr(String birthAddr) {
        this.birthAddr = birthAddr;
    }
}
