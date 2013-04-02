package net.minecraft.src;

import tc.oc.ProjectAresClient;
import net.minecraft.client.Minecraft;

public class mod_Ares extends BaseMod {
    private ProjectAresClient projectClient;

    @Override
    public void load() {
        this.projectClient = new ProjectAresClient();
    }

    @Override
    public String getVersion() {
        return ProjectAresClient.majorVersion + "." + ProjectAresClient.minorVersion + "." + ProjectAresClient.revisionVersion;
    }

    @Override
    public String getName() {
        return "Unoffical Project Ares Client";
    }

    @Override
    public void clientConnect(NetClientHandler handler) {
        this.projectClient.clientConnect(handler);
    }

    @Override
    public void clientDisconnect(NetClientHandler handler) {
        this.projectClient.clientDisconnect(handler);
    }

    @Override
    public boolean onTickInGame(float tick, Minecraft mc) {
        this.projectClient.onTickInGame(tick, mc);
        return true;
    }

    @Override
    public boolean onTickInGUI(float tick, Minecraft mc, GuiScreen screen) {
        this.projectClient.onTickInGUI(tick, mc, screen);
        return true;
    }

    @Override
    public void keyboardEvent(KeyBinding key) {
        this.projectClient.keyboardEvent(key);
    }

    @Override
    public void serverCustomPayload(NetServerHandler serverHandler, Packet250CustomPayload packet) {
        this.projectClient.serverCustomPayload(serverHandler, packet);
    }
}
