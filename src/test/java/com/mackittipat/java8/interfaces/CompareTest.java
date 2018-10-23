package com.mackittipat.java8.interfaces;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest {

    @Test
    public void testComparable() {

        Person p1 = new Person();
        p1.setAge(29);
        p1.setName("Aaa");

        Person p2 = new Person();
        p2.setAge(21);
        p2.setName("Bbb");

        Person p3 = new Person();
        p3.setAge(40);
        p3.setName("Ccc");

        List<Person> personList = Arrays.asList(p1, p2, p3);
        Collections.sort(personList);

        Assert.assertEquals(p2.getName(), personList.get(0).getName());
        Assert.assertEquals(p1.getName(), personList.get(1).getName());
        Assert.assertEquals(p3.getName(), personList.get(2).getName());

    }

    @Test
    public void testComparator() {
        Person p1 = new Person();
        p1.setAge(29);
        p1.setName("Zzz");

        Person p2 = new Person();
        p2.setAge(21);
        p2.setName("Ddd");

        Person p3 = new Person();
        p3.setAge(40);
        p3.setName("Ggg");

        List<Person> personList = Arrays.asList(p1, p2, p3);
//        Collections.sort(personList, (Comparator.comparingInt(Person::getAge)));
        Collections.sort(personList, ((o1, o2) -> o1.getAge() - o2.getAge()));

        Assert.assertEquals(p2.getAge(), personList.get(0).getAge());
        Assert.assertEquals(p1.getAge(), personList.get(1).getAge());
        Assert.assertEquals(p3.getAge(), personList.get(2).getAge());
    }

    @Test
    public void testComparatorByName() {
        Person p1 = new Person();
        p1.setAge(29);
        p1.setName("Zzz");

        Person p2 = new Person();
        p2.setAge(21);
        p2.setName("Ddd");

        Person p3 = new Person();
        p3.setAge(40);
        p3.setName("Ggg");

        List<Person> personList = Arrays.asList(p1, p2, p3);
        Collections.sort(personList, new PersonNameComparator());

        Assert.assertEquals(p2.getName(), personList.get(0).getName());
        Assert.assertEquals(p3.getName(), personList.get(1).getName());
        Assert.assertEquals(p1.getName(), personList.get(2).getName());
    }

    @Test
    public void testComparatorByAge() {
        Person p1 = new Person();
        p1.setAge(29);
        p1.setName("Zzz");

        Person p2 = new Person();
        p2.setAge(21);
        p2.setName("Ddd");

        Person p3 = new Person();
        p3.setAge(40);
        p3.setName("Ggg");

        List<Person> personList = Arrays.asList(p1, p2, p3);
        Collections.sort(personList, new PersonAgeComparator());

        Assert.assertEquals(p2.getAge(), personList.get(0).getAge());
        Assert.assertEquals(p1.getAge(), personList.get(1).getAge());
        Assert.assertEquals(p3.getAge(), personList.get(2).getAge());
    }

    public class Person implements Comparable<Person> {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Person p) {
            return getAge() - p.getAge();
        }
    }

    public class PersonNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }

    public class PersonAgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    }
}
