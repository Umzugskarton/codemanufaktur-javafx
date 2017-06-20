package json;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ServerCommands {

  public static JSONObject registerCommand(String message, boolean success) {
    JSONObject obj = new JSONObject();

    obj.put("command", "register");
    obj.put("success", success);
    obj.put("message", message);

    return obj;
  }

  public static JSONObject loginCommand(String message, boolean success) {
    JSONObject obj = new JSONObject();

    obj.put("command", "login");
    obj.put("success", success);
    obj.put("message", message);

    return obj;
  }

  public static JSONObject logoutCommand(boolean success) {
    JSONObject obj = new JSONObject();

    obj.put("command", "login");
    obj.put("success", success);

    return obj;
  }

  public static JSONObject userlistCommand(JSONArray users) {
    JSONObject obj = new JSONObject();

    obj.put("command", "userlist");
    obj.put("users",users);

    return obj;
  }
}
