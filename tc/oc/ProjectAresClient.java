package tc.oc;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.Packet250CustomPayload;

public class ProjectAresClient {

    public static final int majorVersion = 0;
    public static final int minorVersion = 0;
    public static final int revisionVersion = 0;

    /**
     * This is called on server join This method sets if the user is on Project Ares or not
     */
    public void clientConnect(NetClientHandler handler) {

    }

    /**
     * This Method is called when the client disconnects from a server; It is also called when the program is exited from ingame
     */
    public void clientDisconnect(NetClientHandler handler) {

    }

    /**
     * On a game tick this method is called; It is important to include multi-threading
     */
    public boolean onTickInGame(float tick, Minecraft mc) {
        return true;
    }

    /**
     * This Method is called every tick a gui is open
     * 
     * @param screen the current screen shown
     */
    public boolean onTickInGUI(float tick, Minecraft mc, GuiScreen screen) {
        return true;
    }

    /**
     * Called when a key that has been registered has been pressed.
     * 
     * @param key the key binding of what the user has pressed
     */
    public void keyboardEvent(KeyBinding key) {

    }

    public void serverCustomPayload(NetServerHandler serverHandler, Packet250CustomPayload packet) {

    }
}
