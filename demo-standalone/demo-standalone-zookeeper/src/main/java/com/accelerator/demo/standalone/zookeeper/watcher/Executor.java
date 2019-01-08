package com.accelerator.demo.standalone.zookeeper.watcher;

import com.accelerator.demo.standalone.zookeeper.Constants;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;

import java.io.IOException;

public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(Executor.class);

    DataMonitor dm;

    public Executor(String hostPort, String zNode) throws KeeperException, IOException {
        ZooKeeper zk = new ZooKeeper(hostPort, 3000, this);
        dm = new DataMonitor(zk, zNode, this);
    }

    public static void main(String[] args) {
        String zNode = "/test";
        try {
            new Executor(Constants.ZK_HOSTS, zNode).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent event) {
        dm.process(event);
    }

    public void run() {
        try {
            synchronized (this) {
                while (!dm.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
        }
    }

    public void closing(int rc) {
        synchronized (this) {
            notifyAll();
        }
    }

    public void exists(byte[] data) {
        if (data != null) {
            LOG.info(new String(data));
        }
    }
}