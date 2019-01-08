package com.accelerator.demo.standalone.jgit;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class GitCommit {

    public static void main(String[] args) throws Exception {
        Git git = Git.open(Constants.GIT_DIRECTORY);
        File readme = new File(Constants.GIT_DIRECTORY, "readme.md");
        FileUtils.write(readme, "测试提交！", StandardCharsets.UTF_8);
        git.add().addFilepattern("readme.md").call();
        git.commit().setMessage("测试提交！").call();
    }

}
