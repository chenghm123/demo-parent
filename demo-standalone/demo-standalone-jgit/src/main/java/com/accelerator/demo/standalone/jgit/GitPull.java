package com.accelerator.demo.standalone.jgit;

import org.eclipse.jgit.api.Git;

public class GitPull {

    public static void main(String[] args) throws Exception {
        Git git = Git.open(Constants.GIT_DIRECTORY);
        git.pull().call();
    }

}
