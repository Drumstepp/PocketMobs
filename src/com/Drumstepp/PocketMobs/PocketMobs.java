package com.Drumstepp.PocketMobs;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PocketMobs extends JavaPlugin {

	public static Plugin pluginInstance;
	
	PocketMobs() {
		pluginInstance = this;
	}
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EntityInteractListener(), this);
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
		
	}
}
