package controllers;


import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class SqoopSSH {

public  int sqoopexecute(String sqoopcommand) throws Exception {

    ConnBean cb = new ConnBean("centos6.qubida.io", "root", "Qubida1%%");


    // Put the ConnBean instance as parameter for SSHExec static method getInstance(ConnBean) to retrieve a singleton SSHExec instance
    SSHExec ssh = SSHExec.getInstance(cb);
    // Connect to server
    ssh.connect();
    CustomTask sampleTask1 = new ExecCommand("echo $SSH_CLIENT"); // Print Your Client IP By which you connected to ssh server on Horton Sandbox
    System.out.println(ssh.exec(sampleTask1));
    CustomTask sampleTask2 = new ExecCommand(sqoopcommand);
    ssh.exec(sampleTask2);
    ssh.disconnect();
    return 4;
}
}