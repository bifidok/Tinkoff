package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactParser {
    private ContactParser() {

    }

    public static List<Person> parseContacts(List<String> people, String sortType) {
        List<Person> contacts = new ArrayList<>();
        for (String person : people) {
            var split = person.split(" ");
            var name = split[0];
            var surname = split.length == 2 ? split[1] : "";
            contacts.add(new Person(name, surname));
        }
        contacts.sort((o1, o2) -> {
            String o1CompareTo;
            String o2CompareTo;
            if (o1.surname().equals("")) {
                o1CompareTo = o1.name();
            } else {
                o1CompareTo = o1.surname();
            }
            if (o2.surname().equals("")) {
                o2CompareTo = o2.name();
            } else {
                o2CompareTo = o2.surname();
            }
            return o1CompareTo.charAt(0) - o2CompareTo.charAt(0);
        });
        if (sortType.equals("DESC")) {
            Collections.reverse(contacts);
        }
        return contacts;
    }
}
