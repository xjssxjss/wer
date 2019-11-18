package com.wer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WerApplicationTests {

    @Test
    public void testList(){
        List list=  new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");

        list.stream().forEach((item)->{
            System.out.println(item);
        });
    }
}
