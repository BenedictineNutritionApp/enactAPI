package enactApp.enactAPI.data.translator;


import enactApp.enactAPI.data.model.User;
import enactApp.enactAPI.web.models.UserView;

public class UserTranslator {
    public static UserView entityToView(final User user) {
        return UserView.builder()
                .firstName(user.getFirstName())
                .avatarId(user.getAvatarId())
                .build();
    }


}
