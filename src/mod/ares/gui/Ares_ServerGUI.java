package mod.ares.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.LanServer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringTranslate;

public class Ares_ServerGUI extends GuiScreen {
	ArrayList<Ares_ThreadPollServers> serverList;
	int menuButtons = 3;
	int serverListSpace = 10;

	/**
	 * Default constructor
	 */
	public Ares_ServerGUI() {
		// Construct the server polls here.
		serverList = new ArrayList<Ares_ThreadPollServers>();
		// add all the servers
		serverList.add(new Ares_ThreadPollServers("alpha.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("beta.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("gamma.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("delta.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("epsilon.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("zeta.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("eta.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("theta.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("iota.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("kappa.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("lambda.oc.tc", 25565));
		serverList.add(new Ares_ThreadPollServers("nostalgia.oc.tc", 25565));
		// update all their info
		Thread thread = new Thread() {
			public void run() {
				runServerPolls();
			}
		};
		thread.start();
	}

	/**
	 * This is init of the gui when it is about to get drawn. You should only
	 * have buttons/control elements in here.
	 */
	public void initGui() {
		// clear the list
		this.controlList.clear();
		// top row buttons
		this.controlList.add(new GuiButton(2, this.width - (3 * 75 + 16), 5,
				75, 20, "Refresh"));
		this.controlList.add(new GuiButton(1, this.width - (2 * 75 + 13), 5,
				75, 20, "Old Menu"));
		this.controlList.add(new GuiButton(0, this.width - (75 + 10), 5, 75,
				20, "Cancel"));
		
		//this is a copy of the server list drawing.
		//the buttons need to be drawn here because they only need to be drawn once.
		//make sure this matches the draw method
		int height = 25;
		int width = 40;
		for (int server=0; server<serverList.size();server++) {
			if(height+(3*fontRenderer.FONT_HEIGHT+serverListSpace)>this.height){
				width+=200;
				height = 25;
			}
			height += 2*fontRenderer.FONT_HEIGHT+serverListSpace;
			this.controlList.add(new GuiButton(server+menuButtons, width-35, height-5, 30,20, "Play"));
			height += fontRenderer.FONT_HEIGHT;
		}
	}

	/**
	 * This method is a override method for drawing a gui All "painting" should
	 * take place in here
	 */
	public void drawScreen(int x, int y, float f) {
		drawDefaultBackground();
		drawGradientRect(5, 3, this.width - 5, 20 + 6, 1615855616, -1602211792);
		// drawGradientRect(5, 3, this.width-5, 20+3, -1073741824,-1073741824);
		// title
		drawString(this.fontRenderer, "Project Ares Servers", 10, 10, 16777215);

		// server info drawing
		int height = 25;
		int width = 40;
		for (int server=0; server<serverList.size();server++) {
			if(height+(3*fontRenderer.FONT_HEIGHT+serverListSpace)>this.height){
				width+=200;
				height = 25;
			}
			drawString(this.fontRenderer, "�4" + serverList.get(server).serverIP,width, height += fontRenderer.FONT_HEIGHT+serverListSpace, 16777215);
			drawString(this.fontRenderer, serverList.get(server).MOTD, width,height += fontRenderer.FONT_HEIGHT, 16777215);
			//would have added button here
			drawString(this.fontRenderer,"�f" + serverList.get(server).populationInfo + "  �7"+ serverList.get(server).pingToServer, width,height += fontRenderer.FONT_HEIGHT, 16777215);

		}
		// tells the super class to paint everything
		super.drawScreen(x, y, f);
	}

	/**
	 * If a button is clicked this method gets called. The id is the number
	 * given to the button during init.
	 */
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			// cancel
			if (par1GuiButton.id == 0) {
				this.mc.displayGuiScreen(new GuiMainMenu());
			}
			// old menu
			else if (par1GuiButton.id == 1) {
				GuiListener.toggleMultiGUI(false);
				this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
			}
			// refresh
			else if (par1GuiButton.id == 2) {
				Thread thread = new Thread() {
					public void run() {
						runServerPolls();
					}
				};
				thread.start();
			}
			else if(par1GuiButton.id <= serverList.size()-1+menuButtons){
				String serverip = serverList.get(par1GuiButton.id-menuButtons).serverIP;
				String serverport = Integer.toString(serverList.get(par1GuiButton.id-menuButtons).port);
				ServerData joinServer = new ServerData(serverip,serverip+":"+serverport);
				//connect
				mc.displayGuiScreen(new GuiConnecting(this.mc, joinServer));
			}
		}
	}

	/**
	 * Go through all the servers and poll for new info
	 */
	private void runServerPolls() {
		for (Ares_ThreadPollServers temp : serverList) {
			temp.run();
		}
	}
}
