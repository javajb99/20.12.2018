/**
 * Created by Itayh on 9/16/2015.
 */
public class MySettings {
    private static String CLASS_NAME;
    protected boolean vibrateOn;
    public boolean isVibrateOn()
    {
        return vibrateOn;
    }
    public void SetVibrate(boolean vibrate)
    {
        vibrateOn = vibrate;
    }
    public Settings()
    {
        CLASS_NAME = getClass().getName();
    }
}
