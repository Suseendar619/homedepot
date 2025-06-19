click(Pattern("OKBtn-2.png").similar(0.81));
wait(2000);
doubleClick(Pattern("OM_Display-3.png").similar(0.81).targetOffset(0,-7));

type(Pattern("UserName-2.png").similar(0.81));
type(Pattern("Password-2.png").similar(0.77));
click(Pattern("OM_OKBtn-2.png").similar(0.82));
click(Pattern("ToolsBtn-2.png").similar(0.72));
click(Pattern("WaveInfoBtn-2.png").similar(0.76).targetOffset(0,-10));
click(Pattern("pickWave-2.png").similar(0.77).targetOffset(0,-2));
click(Pattern("RetrieveWave-2.png").similar(0.71));

click(Pattern("Container_Status-2.png").similar(0.77).targetOffset(0,4));

click(Pattern("Launch_Sim_3.png").similar(0.86).targetOffset(0,-5));
click(Pattern("Minimise_OM-2.png").similar(0.76).targetOffset(0,-1));

click(Pattern("I_Icon-2.png").similar(0.86).targetOffset(5,0));

hover(Pattern("5th_sim-2.png").similar(0.81));
click(Pattern("Sim_3-2.png").similar(0.75));

click(Pattern("Induction_Source-2.png").similar(0.95));

type(Pattern("Totenumber_text-2.png").similar(0.90));

type(Pattern("Container_Tote_nbr-2.png").similar(0.92));

click(Pattern("Scan_Btn-2.png").similar(0.82));
click(Pattern("Hand_Scanner_3.png").similar(0.93));
click(Pattern("Hand_Scanner-2.png").similar(0.92));
Click(Pattern("SendBtn_putwall-2.png").similar(0.80));
click(Pattern("Putwall_Icon-2.png").similar(0.73));
click(Pattern("toteNbr_txt-2.png").similar(0.87));
click(Pattern("Tote1-2.png").similar(0.76));
click(Pattern("Tote2-2.png").similar(0.80));
click(Pattern("Tote3-2.png").similar(0.83));
click(Pattern("Tote4-2.png").similar(0.81));
click(Pattern("Tote5-2.png").similar(0.77));
click(Pattern("Item_Delete.png").similar(0.91));
click(Pattern("P1-2.png").similar(0.79));
click(Pattern("PushBtn-2.png").similar(0.90));
click(Pattern("PullBtn.png").exact());