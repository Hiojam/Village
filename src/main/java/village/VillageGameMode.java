package village;

import com.mongodb.client.model.Filters;
import org.lydark.api.Api;
import org.lydark.api.data.gamemodes.GameMode;
import org.lydark.api.data.players.IStats;
import org.lydark.api.data.players.LydarkPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class VillageGameMode extends GameMode {

    public static final String ID = "village";

    private final HashMap<String, VillageStats> stats = new HashMap<>();

    public VillageGameMode(){
        super(ID);
        ArrayList<VillageStats> statsArr = Api.getInstance().database.findMany("Stats", s -> s.getGameMode().equals(getId()),VillageStats.class);
        statsArr.forEach(s -> stats.put(s.getOwnerName(), s));
    }

    @Override
    public IStats getStatsFor(LydarkPlayer lydarkPlayer) {
        if (!stats.containsKey(lydarkPlayer.getName())) {
            VillageStats stats = new VillageStats(lydarkPlayer.getName(), getId());
            Api.getInstance().database.insertOne("Stats", stats);
            this.stats.put(stats.getOwnerName(), stats);
        }
        return stats.get(lydarkPlayer.getName());
    }


    @Override
    public void saveStats(IStats stats) {
        Api.getInstance().database.replaceOneFast("Stats", Filters.and(Filters.eq("owner", stats.getOwnerName()), Filters.eq("gameMode", stats.getGameMode())), stats);
    }
}
