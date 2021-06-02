package village;

import org.lydark.api.data.players.IStats;

public class VillageStats implements IStats {

    private final String owner;
    private final String gameMode;
    private double vilcoins;

    public VillageStats(String owner, String gameModeId) {
        this.owner = owner;
        gameMode = gameModeId;
    }

    @Override
    public String getOwnerName() {
        return owner;
    }

    @Override
    public long getPlayTime() {
        return 0;
    }

    @Override
    public void setPlayTime(long l) {

    }

    @Override
    public int getWins() {
        return 0;
    }

    @Override
    public void setWins(int i) {

    }

    @Override
    public int getLost() {
        return 0;
    }

    @Override
    public void setLost(int i) {

    }

    @Override
    public double getCoins() {
        return vilcoins;
    }

    @Override
    public void setCoins(double vilcoins) {
        this.vilcoins = vilcoins;
    }


    @Override
    public String getGameMode() {
        return gameMode;
    }
}
