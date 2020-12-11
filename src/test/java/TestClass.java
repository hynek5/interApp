import com.microfocus.interApp.domain.MailAccount;
import com.microfocus.interApp.domain.Task;
import com.microfocus.interApp.tasks.DefaultCriteria;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class TestClass {

    DefaultCriteria criteria = new DefaultCriteria();
    Task taskG1Read = new Task();
    Task taskG1ReadPending = new Task();
    Task taskG2Write = new Task();

    @Test
    void contextLoads() {
    }

    @Test
    public void compareMailAcc() {
        MailAccount account1 = new MailAccount("hrabik.h");
        MailAccount account2 = new MailAccount("hrabik.h");
        System.out.println(account1.equals(account2));

        Set<MailAccount> mailAccounts = new HashSet<>();
       // mailAccounts.add(account1);
        //System.out.println(mailAccounts.add(account2));

    }

    @Test
    public void defCriteriaTest() {
        System.out.println("GID:" +criteria.runningGid.toString());
        System.out.println("GID:" +criteria.runningType.toString());

        boolean[] key;
        //run task
        taskG1Read.setGid("G1");
        taskG1Read.setType(Task.OperType.READ);

        key = criteria.generateKey(taskG1Read);

        Assert.assertTrue(criteria.compareKeys(key));

    }

    @Test(priority = 1)
    public void defCriteriaTest2() {
        System.out.println("GID:" +criteria.runningGid.toString());
        System.out.println("GID:" +criteria.runningType.toString());

        boolean[] key;
        //do not run task - the same group
        taskG1ReadPending.setGid("G1");
        taskG1ReadPending.setType(Task.OperType.READ);

        key = criteria.generateKey(taskG1ReadPending);

        Assert.assertFalse(criteria.compareKeys(key));

    }

    @Test(priority = 2)
    public void defCriteriaTest3() {
        System.out.println("GID:" +criteria.runningGid.toString());
        System.out.println("GID:" +criteria.runningType.toString());

        boolean[] key;
        //do not run task - the different type
        taskG2Write.setGid("G2");
        taskG2Write.setType(Task.OperType.WRITE);

        key = criteria.generateKey(taskG2Write);
        System.out.println(key[0]);
        System.out.println(key[1]);

        Assert.assertFalse(criteria.compareKeys(key));

    }

    @Test(priority = 3)
    public void defCriteriaTest4() {
        criteria.updateCriteria(taskG1Read);

        criteria.generateKey(taskG2Write);

        System.out.println("GID:" +criteria.runningGid.toString());
        System.out.println("GID:" +criteria.runningType.toString());


        boolean[] key;
        //task still not run, Group ok but type write still running
        key = criteria.generateKey(taskG1ReadPending);
        System.out.println(key[0]);
        System.out.println(key[1]);

        Assert.assertFalse(criteria.compareKeys(key));

    }


}
