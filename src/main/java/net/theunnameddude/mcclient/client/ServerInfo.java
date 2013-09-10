package net.theunnameddude.mcclient.client;

public class ServerInfo {
    int entityId;
    byte difficulty;
    byte dimention;
    byte gameMode;
    String levelType;
    byte maxPlayers;
    public ServerInfo(int entityId, byte difficulty, byte dimention, byte gameMode, String levelType, byte maxPlayers) {
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

    public byte getDifficulty() {
        return difficulty;
    }

    public byte getDimention() {
        return dimention;
    }

    public byte getGameMode() {
        return gameMode;
    }

    public String getLevelType() {
        return levelType;
    }

    public byte getMaxPlayers() {
        return maxPlayers;
    }
}
