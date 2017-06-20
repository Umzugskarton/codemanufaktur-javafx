package chat.view;

import chat.presenter.ChatPresenter;
import javafx.scene.Scene;

/**
 * Created by maditamuller on 19.06.17.
 */
public interface ChatView {

    public void buildChat();

    public void updateChatHistory();

    public Scene getChatScene();

    public void setChatPresenter(ChatPresenter chatPresenter);
}
