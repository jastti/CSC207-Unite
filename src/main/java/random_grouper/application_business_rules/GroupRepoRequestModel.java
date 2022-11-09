package random_grouper.application_business_rules;

import java.util.List;
import java.util.UUID;

public class GroupRepoRequestModel {
    private String name;
    private List<String> interests;
    private final String ID;
    private List<String> members;
    private final boolean random;

    public GroupRepoRequestModel(String name, String id, List<String> interests, List<String> members,
                                 boolean isRandom) {
        this.name = name;
        this.interests = interests;
        this.ID = id;
        this.members = members;
        this.random = isRandom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public String getID() {
        return ID;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public boolean isRandom() {
        return random;
    }
}
