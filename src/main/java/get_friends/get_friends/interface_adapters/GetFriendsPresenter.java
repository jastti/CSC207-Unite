package get_friends.get_friends.interface_adapters;

import get_friends.get_friends.use_case.GetFriendsDsResponseModel;
import get_friends.get_friends.use_case.GetFriendsOutputBoundary;

public class GetFriendsPresenter implements GetFriendsOutputBoundary {
    /**
     * Returns the list of friends that were requested for
     * @param friends List of friends being returned by the interactor
     * @return List of friends
     */
    @Override
    public GetFriendsDsResponseModel prepareFriendsList(GetFriendsDsResponseModel friends) {
        return friends;
    }
}
