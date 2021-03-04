package org.daniels.interview;


import com.google.common.collect.Lists;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleMain {
    public static void main(String[] args) {
        Participant participant1 = new Participant("participant1", 1);
        Participant participant2 = new Participant("participant2", 0);
        Participant participant3 = new Participant("participant3", 0);
        Participant participant4 = new Participant("participant4", 1);
        Participant participant5 = new Participant("participant5", 1);

        Campaign campaign1 = new Campaign();
        campaign1.add(participant1, participant2);

        Campaign campaign2 = new Campaign();
        campaign2.add(participant3, participant4, participant5);

        List<Campaign> campaigns = Lists.newArrayList(campaign1, campaign2);
        List<CustomerProfile> customerProfiles = new ArrayList<>();

        for (Campaign c : campaigns) {
            if (c.isActive()) {
                for (Participant p : c.getParticipants()) {
                    if (p.getCampaignCode() == 1) {
                        customerProfiles.add(p.getCustomerProfile());
                    }
                }
            }
        }

        Function<Participant, CustomerProfile> funPC = p -> p.getCustomerProfile();

        List<CustomerProfile> campaignStream = campaigns.stream()
                .filter(c -> c.isActive())
                // .flatMap(x -> Arrays.stream(x))
                .flatMap(x -> x.getParticipants().stream())
                .filter(p -> p.getCampaignCode() == 1)
                .map(p1 -> p1.getCustomerProfile())
                .collect(Collectors.toList());


        customerProfiles.forEach(System.out::println);
        System.out.println("-----------------------------");
        campaignStream.forEach(System.out::println);
    }

}

class AppleBuilder {
    private String color;
    private int weight;

    public static AppleBuilder create(String color) {
        AppleBuilder builder = new AppleBuilder();
        builder.color = color;
        return builder;
    }

    AppleBuilder with(int weight) {
        this.weight = weight;
        return this;
    }

    public Apple build() {
        Apple apple = new Apple();
        apple.color = color;
        apple.weight = weight;
        return apple;
    }

}

class Apple {
    String color;
    int weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
