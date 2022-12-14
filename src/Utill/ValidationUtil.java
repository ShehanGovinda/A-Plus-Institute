package Utill;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static Object validateForNormalTextFields(LinkedHashMap<TextField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-text-fill: red");
                    //textFieldKey.setStyle("-fx-border-color: darkred");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: green");
            //textFieldKey.setStyle("-fx-border-color: darkred");
        }
        btn.setDisable(false);
        return true;
    }

    public static Object validateForJFXTextFields(LinkedHashMap<JFXTextField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (JFXTextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-text-fill: darkred");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: black");
        }
        btn.setDisable(false);
        return true;
    }

    public static Object validateForJFXPasswordFields(LinkedHashMap<JFXPasswordField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (JFXPasswordField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-text-fill: darkred");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: black");
        }
        btn.setDisable(false);
        return true;
    }
}
