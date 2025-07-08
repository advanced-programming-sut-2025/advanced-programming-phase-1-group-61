package models;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class AlertGenerator {
    public static void showAlert(String title, String message,Stage stage) {
        showAlert(title, message,stage, null);
    }

    public static void showAlert(String title, String message, Stage stage, Runnable callback) {
        Dialog dialog = new Dialog(title, AssetManager.getSkin()) {
            @Override
            protected void result(Object object) {
                if (callback != null) callback.run();
            }
        };
        dialog.text(message).padTop(20);
        dialog.button("OK", true);
        dialog.key(Input.Keys.ENTER, true); // Enter to confirm
        dialog.show(stage);
    }
}
