package utils;

public class getOSInformation {

    public String getOS()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("mac"))
        {
            return "mac";
        }
        else
        {
            if(os.contains("linux"))
            {
                return "linux";
            }
            else
            {
                return "windows";
            }
        }
    }
}
