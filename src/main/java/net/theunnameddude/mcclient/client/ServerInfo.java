package net.theunnameddude.mcclient.client;

public class ServerInfo {
    int entityId;
    short difficulty;
    byte dimention;
    short gameMode;
    String levelType;
    short maxPlayers;
    public ServerInfo(int entityId, short difficulty, byte dimention, short gameMode, String levelType, short maxPlayers) {
        this.entityId = entityId;
        this.difficulty = difficulty;
        this.dimention = dimention;
        this.gameMode = gameMode;
        this.levelType = levelType;
        this.maxPlayers = maxPlayers;
    }

    public int getEntityId() {
        return entityId;
    }

    public short getDifficulty() {
        return difficulty;
    }

    public byte getDimention() {
        return dimention;
    }

    public short getGameMode() {
        return gameMode;
    }

    public String getLevelType() {
        return levelType;
    }

    public short getMaxPlayers() {
        return maxPlayers;
    }
}
