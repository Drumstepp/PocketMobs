package com.Drumstepp.PocketMobs;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PocketMobs extends JavaPlugin {

	public static Plugin pluginInstance;
	
	
	@Override
	public void onEnable() {
		pluginInstance = this;
		getServer().getPluginManager().registerEvents(new EntityInteractListener(this), this);
		getServer().getPluginManager().registerEvents(new BlockInteractListener(this), this);
		//////
		System.out.println("Pocket Mobs is being enabled!");
		System.out.println("""
				                                       /;    ;\\
                                   __  \\\\____//
                                  /{_\\_/   `'\\____
                                  \\___   (o)  (o  }
       _____________________________/          :--'  
   ,-,'`@@@@@@@@       @@@@@@         \\_    `__\\
  ;:(  @@@@@@@@@        @@@             \\___(o'o)
  :: )  @@@@          @@@@@@        ,'@@(  `===='       
  :: : @@@@@:          @@@@         `@@@:
  :: \\  @@@@@:       @@@@@@@)    (  '@@@'
  ;; /\\      /`,    @@@@@@@@@\\   :@@@@@)
  ::/  )    {_----------------:  :~`,~~;
 ;;'`; :   )                  :  / `; ;
;;;; : :   ;                  :  ;  ; :              
`'`' / :  :                   :  :  : :
    )_ \\__;      ";"          :_ ;  \\_\\       `,','
    :__\\  \\    * `,'*         \\  \\  :  \\   *  8`;'*  *
        `^'     \\ :/           `^'  `-^-'   \\v/ :  \\/ 
				
				
				
				""");
	}
	
	public void onDisable() {
		HandlerList.unregisterAll(this);
		System.out.println("PocketMobs has been disabled. Beep Boop.");
	}
}
