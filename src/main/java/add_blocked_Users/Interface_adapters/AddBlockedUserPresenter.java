package add_blocked_Users.Interface_adapters;

import add_blocked_Users.application_business_rules.AddBlockedUserOutputBoundary;
import add_blocked_Users.application_business_rules.AddBlockedUserResponseModel;

public class AddBlockedUserPresenter implements AddBlockedUserOutputBoundary {
    @Override
    public AddBlockedUserResponseModel prepareSuccessView(AddBlockedUserResponseModel response) {
        /*List<String> list = new ArrayList<>(response.getBlockedList());
        Collections.sort(list);
        response.setBlockedList(list);
        return response;*/
        String blockList = response.getBlockedList();
        response.setBlockedList(blockList);
        return response;
    }
    @Override
    public AddBlockedUserResponseModel prepareFailView(String error) {
        throw new AddBlockedUserFailure(error);
    }
}
