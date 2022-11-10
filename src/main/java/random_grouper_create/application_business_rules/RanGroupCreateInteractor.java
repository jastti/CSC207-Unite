package random_grouper_create.application_business_rules;

import database_classes.GroupRepoInt;
import database_classes.GroupRepoRequestModel;
import entities.Group;
import entities.RandomGroupFactory;
import entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RanGroupCreateInteractor implements RanGroupCreateInputBoundary {
    final GroupRepoInt GROUP_REPO_ACCESS;
    final RanGroupCreateOutputBoundary GROUP_CREATE_OUTPUT_BOUNDARY;
    final RandomGroupFactory GROUP_FACTORY;
    // ToDo: for message storage - final MessageRepoInt messageRepoAccess;

    public RanGroupCreateInteractor(GroupRepoInt groupRepoAccess,
                                    RanGroupCreateOutputBoundary groupCreateOutputBoundary,
                                    RandomGroupFactory groupFactory) {
        this.GROUP_REPO_ACCESS = groupRepoAccess;
        this.GROUP_CREATE_OUTPUT_BOUNDARY = groupCreateOutputBoundary;
        this.GROUP_FACTORY = groupFactory;
    }

    @Override
    public RanGroupCreateResponseModel createRanGroup(RanGroupCreateRequestModel requestModel) {
        String groupName = requestModel.getGroupName();
        List<String> groupInterests = requestModel.getInterests();

        // ToDo: change when can see User class and repo + add check to ensure interests match users interests?
        List<User> members = new ArrayList<>();
        User creator = new User(requestModel.getGroupCreator());
        members.add(creator);
        for (String interest : groupInterests) {
            if (!(creator.getInterests().contains(interest))) {
                return GROUP_CREATE_OUTPUT_BOUNDARY.prepareFailView("You have not listed " +
                        interest + " as one of your interests.");
            }
        }

        Group group = GROUP_FACTORY.createNewGroup(groupName, groupInterests, members);

        // ToDo: start message storage for group once have access to message class?

        // ToDo: update profile/user when have access to profile/user class

        List<String> membersString = new ArrayList<>();
        membersString.add(requestModel.getGroupCreator());
        GroupRepoRequestModel repoRequestModel = new GroupRepoRequestModel(groupName, group.getId(),
                groupInterests, membersString, true);
        GROUP_REPO_ACCESS.addGroup(repoRequestModel);

        LocalDateTime creationTime = LocalDateTime.now();
        RanGroupCreateResponseModel groupCreateResponseModel = new RanGroupCreateResponseModel(group.getName(),
                creationTime.toString());

        return GROUP_CREATE_OUTPUT_BOUNDARY.prepareSuccessView(groupCreateResponseModel);
    }
}
