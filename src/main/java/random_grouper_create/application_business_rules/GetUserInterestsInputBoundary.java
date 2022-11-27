package random_grouper_create.application_business_rules;

public interface GetUserInterestsInputBoundary {

    /**
     * Get a user's interests.
     * @param requestModel A data structure containing user's name
     * @return Returns a data structure containing the user's interests.
     */
    GetUserInterestsResponseModel getUserInterests(GetUserInterestsRequestModel requestModel);
}
