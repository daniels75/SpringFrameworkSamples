package org.daniels.demospring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Info {

    public String info() {
        List<String> list = new LinkedList<>();
        return "simple info";
    }

}
