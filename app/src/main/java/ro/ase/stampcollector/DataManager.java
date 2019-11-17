package ro.ase.stampcollector;

import android.icu.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataManager {

    private  static DataManager ourInstance = null;

    private List<StampInfo> mStamps= new ArrayList<>();

    public  static DataManager getInstance(){
        if(ourInstance == null){
            ourInstance = new DataManager();
            ourInstance.initializeStamps();
        }
        return ourInstance;
    }


    public List<StampInfo> getStamps() {
        return mStamps;
    }

    public int createNewStamp(){
        StampInfo stamp = new StampInfo(null, null,
                null, null, 0);
        mStamps.add(stamp);
        return mStamps.size() - 1;
    }

    public void removeStamp(int index){ mStamps.remove(index); }

    public void initializeStamps(){
        mStamps.add(new StampInfo("Romania. 1906. King Karl I.",
                "Romania 1906 Mi 181 King Karl I Sc 190",
                "25 Aug 1906", "black", 2));

        mStamps.add(new StampInfo("Romania. 1932. Carol II of Romania.",
                "Romania 1932 Mi 436 Carol II of Romania Sc 416",
                "15 Jan 1932", "light blue", 5));

        mStamps.add(new StampInfo("Poland. 1967. Tadeusz Kosciuszko.",
                "Poland 1967 Mi 1806-1807 Tadeusz Kosciuszko Sc 1540-1541",
                "23 Mar 1967", "slate green", 1));

        mStamps.add(new StampInfo("Romania. 1955. Birthday of Lenin.",
                "Romania 1955 Mi 1511-1513 Birthday of Lenin Sc 1021-1023",
                "10 Jul 1966", "liliac brown", 10));

        mStamps.add(new StampInfo("Romania. 1936. King Carol II.",
                "Romania 1936 Mi 502 King Carol II Sc 456",
                "18 Nov 1936", "blue", 4));


        mStamps.add(new StampInfo("Indonesia. 1983. Agricultural Census.",
                "Indonesia 1983 Sc 1200-1201 Agricultural Census Mi 1104-1105",
                 "3 Jul 1983", "multicolor", 20));

        mStamps.add(new StampInfo("Bulgaria. 1973. Nicolaus Copernicus.",
                "Bulgaria 1973 Mi 2228 Nicolaus Copernicus Sc 2086",
                "21 Mar 1973", "multicolor", 8));

        mStamps.add(new StampInfo("Belgium. 1997. Royal Museum for Central Africa.",
                "Belgium 1997 Mi 2780 Royal Museum for Central Africa Sc 1673",
                "18 Nov 1997", "multicolor", 7));

        mStamps.add(new StampInfo("U.S. 1936. Arkansas Centennial.",
                "US 1936 Sc 782 Arkansas Centennial Mi 387",
                "7 Sept 1936", "purple", 14));

        mStamps.add(new StampInfo("Italy. 1944. Portrait of " +
                "brothers Bandiera, Attilio and Emilio.",
                "Italy. 1944 Sc 32-34. Portrait of brothers Bandiera," +
                        " Attilio and Emilio. Mi 663-665",
                "12 Dec 1944", "violet", 1));


        mStamps.add(new StampInfo("Turkey. 1919. Commemorating Accession to Throne of Mohammed VI.",
                "Turkey 1919 Sc 568 Commemorating Accession to Throne of Mohammed VI Mi 661",
                "11 Jun 1919", "green", 1));


        mStamps.add(new StampInfo("Mexico. 1864. Miguel Hidalgo.",
                "Mexico 1864 Sc 15A Miguel Hidalgo Mi 15II",
                "1 May 1864", "blue", 2));

        mStamps.add(new StampInfo("Saudi Arabia. 1916. Details of door Panels" +
                " of Mosque El Salih Talay, Cairo.",
                "Saudi Arabia 1916 Hejaz Sc L5 Details of door " +
                        "Panels of Mosque El Salih Talay, Cairo Mi 1",
                "9 Otc 1916", "green", 9));

        mStamps.add(new StampInfo("Vatican. 1929. Papal coat of arms.",
                "Vatican 1929 Sc 2 Papal coat of arms Mi 2",
                "10 Dec 1929", "light green", 1));

        mStamps.add(new StampInfo("Netherlands. 1943. Posthorn and National coat of arms",
                "Netherlands. 1943 Sc 244. Posthorn and National coat of arms. Mi 404",
                "15 Jan 1943", "yellow", 3));

    }




}
