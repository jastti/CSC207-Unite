package entities;

import java.util.List;

public interface GroupFactory {
    /**
     * Create and return a group object for a new group.
     *
     * @param name the name of the group
     * @param interests the interests each group member must possess
     * @param members the members of the group
     */
    Group createNewGroup(String name, List<String> interests, List<User> members);

    /**
     * Create and return a new group object for an existing group.
     *
     * @param name the name of the group
     * @param interests the interests each group member must possess
     * @param members the members of the group
     * @param id the group id
     */
    Group recreateExisitingGroup(String name, List<String> interests, List<User> members, String id);
}