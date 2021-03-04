package org.daniels.interview;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class Campaign {
    final List<Participant> list = Lists.newArrayList();

    public boolean isActive() {
        return true;
    }

    public void add(Participant  ...participant) {
        list.addAll(Arrays.asList(participant));
    }

    public List<Participant> getParticipants() {
        return list;
    }
}
