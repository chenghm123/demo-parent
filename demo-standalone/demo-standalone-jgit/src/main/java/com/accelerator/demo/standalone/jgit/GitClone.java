package com.accelerator.demo.standalone.jgit;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;

public class GitClone {

    public static void main(String[] args) throws Exception {
        FileUtils.deleteQuietly(Constants.GIT_DIRECTORY);
        Git git = Git.cloneRepository()
                .setURI(Constants.GIT_REPOSITORY)
                .setDirectory(Constants.GIT_DIRECTORY)
                .call();
        System.out.println(git.remoteList());
    }

}
