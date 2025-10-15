import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

// Palletes
public class CCR_Colors{

    // Pallete: Terentia
    // General background
    public static Color BG_DARK;
    public static Color TEXT_COLOR;
    public static Color BUTTON_BG;
    public static Color ACCENT;

    // Todo panel colors
    public static Color PANEL_BG;
    public static Color TEXT_BG;
    public static Color CLS_BUTTON_BG;

    // Top bar
    public static Color TOP_BAR_COLOR;

    public static void CCR_ParseColours(String fpath){
        try(BufferedReader reader = new BufferedReader(new FileReader("palletes/" + fpath + ".ccrpal"))){
            String line;

            while((line = reader.readLine()) != null){
                line = line.trim();

                // Skip empty lines or comments
                if(line.isEmpty() || line.startsWith("#") || !line.contains("=")) continue;

                String[] parts = line.split("=");
                if(parts.length != 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                try{
                    // Parse hex value
                    if(value.startsWith("0x") || value.startsWith("0X")){
                        value = value.substring(2);
                    }
                    int rgb = Integer.parseInt(value, 16);

                    // Find field in CCR_Colors
                    Field field = CCR_Colors.class.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(null, new Color(rgb));

                }catch(NoSuchFieldException e){
                    System.err.println("Unknown color field: " + key);
                }catch(NumberFormatException e){
                    System.err.println("Invalid color value for " + key + ": " + value);
                }catch(IllegalAccessException e){
                    System.err.println("Cannot access field: " + key);
                }
            }

        }catch(IOException e){
            System.err.println("Failed to load palette: " + e.getMessage());
        }
    }
}
