package com.accelerator.demo.standalone.common.ftp.pool;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FTPClientPoolTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private ExecutorService executorService = Executors.newFixedThreadPool(15);

    private Set<FTPClient> ftpClients = Sets.newHashSetWithExpectedSize(15);

    private CountDownLatch countDownLatch = new CountDownLatch(100);

    @Resource
    private FTPClientPool ftpClientPool;

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 100; i++) {
            executorService.execute(new FTPClientPoolDownloadRunnable(i));
        }
        countDownLatch.await();
    }

    private class FTPClientPoolDownloadRunnable implements Runnable {

        private int downloadNum;

        private FTPClientPoolDownloadRunnable(int downloadNum) {
            this.downloadNum = downloadNum;
        }

        @Override
        public void run() {
            String downloadFilename = "Num-" + downloadNum
                    + "_Thread-" + Thread.currentThread().getId() + ".jpg";
            try (FTPClient ftpClient = ftpClientPool.borrowFTPClient();
                 OutputStream out = new FileOutputStream(downloadFilename);) {
                if (ftpClients.contains(ftpClient)) {
                    log.info("ftpClient[{}]", ftpClient);
                }
                ftpClients.add(ftpClient);
                log.info("retrieve result:{}", ftpClient.retrieveFile("1.jpg", out));
            } catch (IOException e) {
                log.error("操作失败!", e);
            } finally {
                countDownLatch.countDown();
            }
        }

    }

}
