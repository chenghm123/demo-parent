package com.accelerator.demo.standalone.common.ftp;

import com.google.common.collect.Lists;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class FTPClientHelper {

    private FTPClient buildClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(hostname, port);
        if (!ftpClient.login(username, password)) {
            ftpClient.logout();
            throw new RuntimeException("FTP用户名密码错误！");
        }
        ftpClient.setControlEncoding(StandardCharsets.UTF_8.name());
        // 文件类型,默认是ASCII
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        // 设置被动模式
        ftpClient.enterLocalPassiveMode();
        // 上传超时时间
        ftpClient.setDataTimeout(6000000);
        // 缓冲大小
        ftpClient.setBufferSize(1024);
        if ((!FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))) {
            closeClient(ftpClient);
            throw new RuntimeException("FTP连接失败！");
        }
        return ftpClient;
    }

    private void closeClient(FTPClient ftpClient) {
        if (Objects.nonNull(ftpClient) && ftpClient.isConnected()) {
            IOException unknownIOEx = null;
            try {
                ftpClient.logout();
            } catch (IOException e) {
                unknownIOEx = e;
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    if (Objects.isNull(unknownIOEx)) {
                        unknownIOEx = e;
                    } else {
                        unknownIOEx.addSuppressed(e);
                    }
                }
            }
            if (Objects.nonNull(unknownIOEx)) {
                // throw unknownIOEx;
                // ignored
            }
        }
    }

    public boolean download(String filename, OutputStream out) {
        FTPClient ftpClient = null;
        try {
            ftpClient = this.buildClient();
            return ftpClient.retrieveFile(
                    FilenameUtils.separatorsToUnix(filename),
                    out
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeClient(ftpClient);
        }
    }

    public boolean upload(String filename, InputStream in) {
        FTPClient ftpClient = null;
        try {
            filename = FilenameUtils.separatorsToUnix(filename);
            ftpClient = this.buildClient();
            String modifyTime = ftpClient.getModificationTime(filename);
            if (Objects.isNull(modifyTime)) {
                String filePath = FilenameUtils.getFullPathNoEndSeparator(filename);
                String userPath = ftpClient.printWorkingDirectory();
                List<String> preMkdirs = Lists.newArrayList();
                // 判断文件夹是否需要创建
                while (!ftpClient.changeWorkingDirectory(filePath)) {
                    preMkdirs.add(filePath);
                    int lastSeparator = FilenameUtils.indexOfLastSeparator(filePath);
                    if (lastSeparator < 0) {
                        break;
                    }
                    filePath = filePath.substring(0, lastSeparator);
                    if (StringUtils.isEmpty(filePath)) {
                        break;
                    }
                }
                Collections.reverse(preMkdirs); // 获取文件夹创建顺序
                // 开始创建文件夹
                ftpClient.changeWorkingDirectory(userPath);
                for (String preMkdir : preMkdirs) {
                    if (StringUtils.isNotEmpty(preMkdir)) {
                        log.info("FTPServer[{}:{}]创建文件夹[{}]=>{}",
                                hostname, port,
                                FilenameUtils.concat(userPath, preMkdir),
                                ftpClient.makeDirectory(preMkdir)
                        );
                    }
                }
            } else {
                String updateTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
                String backupSuffix = modifyTime + "_" + updateTime + ".BAK";
                String backupName = filename + "." + backupSuffix;
                ftpClient.rename(filename, backupName);
                log.warn("原文件[{}]已经存在！备份为[{}]",
                        FilenameUtils.getName(filename),
                        FilenameUtils.getName(backupName)
                );
            }
            return ftpClient.storeFile(filename, in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeClient(ftpClient);
        }
    }

    public boolean upload(String filename, byte[] bytes) {
        filename = FilenameUtils.separatorsToUnix(filename);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return upload(filename, in);
    }

    @Setter
    private String hostname;

    @Setter
    private int port = 21;

    @Setter
    private String username;

    @Setter
    private String password;

}
