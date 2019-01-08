package com.accelerator.demo.standalone.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.lib.Ref;

import java.util.List;

public class GitPush {

    public static void main(String[] args) throws Exception {
        Git git = Git.open(Constants.GIT_DIRECTORY);
        ListBranchCommand listBranchCommand = git.branchList();
        List<Ref> branchList = listBranchCommand.call();
        for (Ref ref : branchList) {
            System.out.println(ref.getName());
        }
        git.push().call();
    }

}
