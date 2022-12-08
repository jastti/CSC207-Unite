package Block_User_Application.interface_adapters;

import Block_User_Application.application_business_rules.UserReporterOutputBoundary;
import Block_User_Application.application_business_rules.UserReporterResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserReporterPresenter implements UserReporterOutputBoundary {
    @Override
    public UserReporterResponseModel prepareSuccessView(UserReporterResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getTime());
        response.setTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    public UserReporterResponseModel prepareFailView(String error){
        throw new UserReporterFailure(error);
    }

}
