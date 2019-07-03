package com.duwei.demo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 列表分割
 *
 * @author dw
 */
public class ListPartition {

    public static void main(String[] args) {
        // 1.初始化一个组织列表
        List<Organ> organs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Organ organ = new Organ(i + "", null, "organ" + i, "组织-" + i);
            organs.add(organ);
        }

        Map<Boolean, List<Organ>> partitionResult = organs
                .stream()
                .collect(Collectors.partitioningBy(
                        organ -> (Integer.parseInt(organ.getId()) % 2 == 0)));
        System.out.println(partitionResult.get(Boolean.TRUE));
        System.out.println(partitionResult.get(Boolean.FALSE));

        //此外还有  可以分为多组 Collectors.groupingBy()
    }

}

/**
 * 组织
 */
class Organ {

    private String id;
    private String parentId;
    private String code;
    private String name;


    public Organ(String id, String parentId, String code, String name) {
        this.id = id;
        this.parentId = parentId;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organ{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
