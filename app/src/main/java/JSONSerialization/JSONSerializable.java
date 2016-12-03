package JSONSerialization;

import org.json.JSONObject;

/**
 * Created by David on 2016-10-01.
 */
public interface JSONSerializable
{
    public JSONObject toJSON();
    public void initializeFromJSON(JSONObject jsonObject);
}
