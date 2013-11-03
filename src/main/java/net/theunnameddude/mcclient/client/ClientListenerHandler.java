package net.theunnameddude.mcclient.client;

import net.theunnameddude.mcclient.api.ClientListener;
import net.theunnameddude.mcclient.api.ProtocolStatus;
import net.theunnameddude.mcclient.protocol.base.PacketPluginMessageBase;
import net.theunnameddude.mcclient.protocol.base.PacketRespawnBase;
import net.theunnameddude.mcclient.protocol.base.PacketTeamBase;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClientListenerHandler extends ClientListener {
    public ArrayList<ClientListener> listeners = new ArrayList<ClientListener>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void onPing() {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onPing();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onDisconnect() {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onDisconnect();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onDisconnected() {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onDisconnected();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onAuthComplete() {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onAuthComplete();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onKick(String reason) {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onKick(reason);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onTeamPacket(PacketTeamBase packet) {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onTeamPacket(packet);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onChat(JSONObject message) {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onChat(message);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onPluginMessage(PacketPluginMessageBase packet) {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onPluginMessage( packet );
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onRespawn(PacketRespawnBase packet) {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onRespawn(packet);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onConnected() {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onConnected();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onAuthFail(String response) {
        lock.readLock().lock();
        try {
            for( ClientListener listener : listeners ) {
                listener.onAuthFail( response );
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onServerInfo(ServerInfo info) {
        lock.readLock().lock();
        try {
            for( ClientListener listener : listeners ) {
                listener.onServerInfo( info );
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void onStatusChange(ProtocolStatus status) {
        lock.readLock().lock();
        try {
            for ( ClientListener listener : listeners ) {
                listener.onStatusChange( status );
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void addListener(ClientListener listener) {
        listeners.add( listener );
    }
}
